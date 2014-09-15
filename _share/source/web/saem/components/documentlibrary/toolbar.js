/**
 * YUI Library aliases
 */
var Dom = YAHOO.util.Dom,
Event = YAHOO.util.Event,
Element = YAHOO.util.Element;

/**
 * Alfresco Slingshot aliases
 */
var $html = Alfresco.util.encodeHTML,
$combine = Alfresco.util.combinePaths,
$siteURL = Alfresco.util.siteURL;

var destination; 

/**
 * Preferences
 */
var PREFERENCES_ROOT = "org.alfresco.share.documentList",
PREF_HIDE_NAVBAR = PREFERENCES_ROOT + ".hideNavBar";

/**
 * Augment prototype with main class implementation, ensuring overwrite is enabled
 */
YAHOO.lang.augmentObject(Alfresco.DocListToolbar.prototype,
		{
	/**
	 * Fired by YUI when parent element is available for scripting.
	 * Component initialisation, including instantiation of YUI widgets and event listener binding.
	 *
	 * @method onReady
	 */
	onReady: function DLTB_onReady()
{
		// Create Content menu button
		if (Dom.get(this.id + "-createContent-button"))
		{
			// Create menu button that
			this.widgets.createContent = Alfresco.util.createYUIButton(this, "createContent-button", this.onCreateContent,
					{
				type: "menu",
				menu: "createContent-menu",
				lazyloadmenu: false,
				disabled: true,
				value: "CreateChildren"
					});

			// Make sure we load sub menu lazily with data on each click
			var createContentMenu = this.widgets.createContent.getMenu(),
			groupIndex = 0;

			// Create content actions
			if (this.options.createContentActions.length !== 0)
			{
				var menuItems = [], menuItem, content, url, config, html, li;
				for (var i = 0; i < this.options.createContentActions.length; i++)
				{
					// Create menu item from config
					content = this.options.createContentActions[i];
					config = { parent: createContentMenu };
					url = null;

					// Check config type
					if (content.type == "javascript")
					{
						config.onclick =
						{
								fn: function(eventName, eventArgs, obj)
								{
									// Copy node so we can safely pass it to an action
									var node = Alfresco.util.deepCopy(this.doclistMetadata.parent);

									// Make it more similar to a usual doclib action callback object
									var currentFolderItem = {
											nodeRef: node.nodeRef,
											node: node,
											jsNode: new Alfresco.util.Node(node)
									};
									this[obj.params["function"]].call(this, currentFolderItem);
								},
								obj: content,
								scope: this
						};

						url = '#';
					}
					else if (content.type == "pagelink")
					{
						url = $siteURL(content.params.page);
					}
					else if (content.type == "link")
					{
						url = content.params.href;
					}

					// Create menu item
					html = '<a href="' + url + '" rel="' + content.permission + '"><span style="background-image:url(' + Alfresco.constants.URL_RESCONTEXT + 'components/images/filetypes/' + content.icon + '-file-16.png)" class="' + content.icon + '-file">' + this.msg(content.label) + '</span></a>';
					li = document.createElement("li");
					li.innerHTML = html;
					menuItem = new YAHOO.widget.MenuItem(li, config);

					menuItems.push(menuItem);
				}
				createContentMenu.addItems(menuItems, groupIndex);
				groupIndex++;
			}

			// Create content by template menu item
			if (this.options.createContentByTemplateEnabled)
			{
				// Create menu item elements
				var li = document.createElement("li");
				li.innerHTML = '<a href="#"><span>' + this.msg("menu.create-content.by-template-node") + '</span></a>';

				// Make sure to stop clicks on the sub menu link to close the entire menu
				YAHOO.util.Event.addListener(Selector.query("a", li, true), "click", function(e)
						{
					Event.preventDefault(e);
					Event.stopEvent(e);
						});

				// Create placeholder menu
				var div = document.createElement("div");
				div.innerHTML = '<div class="bd"><ul></ul></div>';

				// Add menu item
				var createContentByTemplate = new YAHOO.widget.MenuItem(li, {
					parent: createContentMenu,
					submenu: div
				});
				createContentMenu.addItems([ createContentByTemplate ], groupIndex);
				groupIndex++;

				// Make sure that the available template are lazily loaded
				var templateNodesMenus = this.widgets.createContent.getMenu().getSubmenus(),
				templateNodesMenu = templateNodesMenus.length > 0 ? templateNodesMenus[0] : null;
				if (templateNodesMenu)
				{
					templateNodesMenu.subscribe("beforeShow", this.onCreateByTemplateNodeBeforeShow, this, true);
					templateNodesMenu.subscribe("click", this.onCreateByTemplateNodeClick, this, true);
				}
			}

			// Render menu with all new menu items
			createContentMenu.render();
			this.dynamicControls.push(this.widgets.createContent);
		}

		// New Folder button: user needs "create" access
		this.widgets.newFolder = Alfresco.util.createYUIButton(this, "newFolder-button", this.onNewFolder,
				{
			disabled: true,
			value: "CreateChildren"
				});
		this.dynamicControls.push(this.widgets.newFolder);

		// File Upload button: user needs  "CreateChildren" access
		this.widgets.fileUpload = Alfresco.util.createYUIButton(this, "fileUpload-button", this.onFileUpload,
				{
			disabled: true,
			value: "CreateChildren"
				});
		this.dynamicControls.push(this.widgets.fileUpload);

		// SAEM-73 New archive button
		// New archive button: user needs "create" access
		this.widgets.newArchive = Alfresco.util.createYUIButton(this, "newArchive-button", this.onNewArchive,
				{
			disabled: true,
			value: "CreateChildren"
				});
		this.dynamicControls.push(this.widgets.newArchive);


		// Sync to Cloud button
		this.widgets.syncToCloud = Alfresco.util.createYUIButton(this, "syncToCloud-button", this.onSyncToCloud,
				{
			disabled: true,
			value: "CreateChildren"
				});
		this.dynamicControls.push(this.widgets.syncToCloud);

		// Unsync from Cloud button
		this.widgets.unsyncFromCloud = Alfresco.util.createYUIButton(this, "unsyncFromCloud-button", this.onUnsyncFromCloud,
				{
			disabled: true,
			value: "CreateChildren"
				});
		this.dynamicControls.push(this.widgets.unsyncFromCloud);

		// Selected Items menu button
		this.widgets.selectedItems = Alfresco.util.createYUIButton(this, "selectedItems-button", this.onSelectedItems,
				{
			type: "menu", 
			menu: "selectedItems-menu",
			lazyloadmenu: false,
			disabled: true
				});
		this.dynamicControls.push(this.widgets.selectedItems);

		// Hide/Show NavBar button
		this.widgets.hideNavBar = Alfresco.util.createYUIButton(this, "hideNavBar-button", this.onHideNavBar,
				{
			type: "checkbox",
			checked: !this.options.hideNavBar
				});
		if (this.widgets.hideNavBar !== null)
		{
			this.widgets.hideNavBar.set("title", this.msg(this.options.hideNavBar ? "button.navbar.show" : "button.navbar.hide"));
			Dom.setStyle(this.id + "-navBar", "display", this.options.hideNavBar ? "none" : "block");
			this.dynamicControls.push(this.widgets.hideNavBar);
		}

		// RSS Feed link button
		this.widgets.rssFeed = Alfresco.util.createYUIButton(this, "rssFeed-button", null, 
				{
			type: "link"
				});
		this.dynamicControls.push(this.widgets.rssFeed);

		// Folder Up Navigation button
		this.widgets.folderUp =  Alfresco.util.createYUIButton(this, "folderUp-button", this.onFolderUp,
				{
			disabled: true,
			title: this.msg("button.up")
				});
		this.dynamicControls.push(this.widgets.folderUp);

		// DocLib Actions module
		this.modules.actions = new Alfresco.module.DoclibActions();

		// Reference to Document List component
		this.modules.docList = Alfresco.util.ComponentManager.findFirst("Alfresco.DocumentList");

		// Preferences service
		this.services.preferences = new Alfresco.service.Preferences();

		// Finally show the component body here to prevent UI artifacts on YUI button decoration
		Dom.setStyle(this.id + "-body", "visibility", "visible");
},


/**
 * New Archive button click handler
 *
 * @method onNewArchive
 * @param e {object} DomEvent
 * @param p_obj {object} Object passed back from addListener method
 */
onNewArchive: function DLTB_onNewArchive(e, p_obj)
{
	destination = this.doclistMetadata.parent.nodeRef;

	// Intercept before dialog show
	var doBeforeDialogShow = function DLTB_onNewFolder_doBeforeDialogShow(p_form, p_dialog)
	{
		Dom.get(p_dialog.id + "-dialogTitle").innerHTML = this.msg("label.new-archive.title");
		Dom.get(p_dialog.id + "-dialogHeader").innerHTML = this.msg("label.new-archive.header");
		Dom.get(p_dialog.id + "-destination-hidden").value = destination;

	};

	var createArchive = new Alfresco.module.SimpleDialog(this.id + "-createArchive");

	createArchive.setOptions(
			{
				width: "40em",
				templateUrl:  Alfresco.constants.URL_SERVICECONTEXT + "components/form/create-archive-popup",
				actionUrl: Alfresco.constants.PROXY_URI + "/saem/submit-create-archive-popup",
				destroyOnHide: true,
				doBeforeDialogShow:
				{
					fn: doBeforeDialogShow,
					scope: this
				},
				onSuccess:
				{
					fn: function DLTB_onNewArchive_success(response)
				{
						var currentNodeRef = response.config.dataObj.destination;
						var profileNodeRef = response.config.dataObj.profils;
						window.location.assign(Alfresco.constants.URL_CONTEXT + "page/archive-management?" + "destination=" + currentNodeRef + "&profil=" + profileNodeRef);
				},
				scope: this
				},
				onFailure:
				{
					fn: function DLTB_onNewArchive_failure(response)
				{
						if (response)
						{
							var archiveName = Dom.get(this.id + "-profils").value;
							Alfresco.util.PopupManager.displayMessage(
									{
										text: this.msg("message.new-archive.failure", archiveName)
									});
						}
						else
						{
							Alfresco.util.PopupManager.displayMessage(
									{
										text: this.msg("message.failure")
									});
						}
				},
				scope: this
				}
			}).show();

}
		}, true);