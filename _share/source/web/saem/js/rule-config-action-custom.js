
/**
 * RuleConfigActionCustom.
 *
 * @namespace YourCompany
 * @class YourCompany.RuleConfigActionCustom
 */
(function()
		{
	Alfresco.RuleConfigActionCustom = function(htmlId)
	{
		Alfresco.RuleConfigActionCustom.superclass.constructor.call(this, htmlId);

		// Re-register with our own name
		this.name = "asalae-elimination";
		Alfresco.util.ComponentManager.reregister(this);

		// Instance variables
		this.customisations = YAHOO.lang.merge(this.customisations, Alfresco.RuleConfigActionCustom.superclass.customisations);
		this.renderers = YAHOO.lang.merge(this.renderers, Alfresco.RuleConfigActionCustom.superclass.renderers);

		return this;
	};

	YAHOO.extend(Alfresco.RuleConfigActionCustom, Alfresco.RuleConfigAction,
			{

		/**
		 * CUSTOMISATIONS
		 */

		customisations:
		{

			LinkCategory:
			{
				text: function(configDef, ruleConfig, configEl)
				{
					// Setting _type to "category" will make the nodeRef get resolved to the category's name
					this._getParamDef(configDef, "category-value")._type = "category";
					return configDef;
				},
				edit: function(configDef, ruleConfig, configEl)
				{
					// Hide all parameters
					this._hideParameters(configDef.parameterDefinitions);
					// ...and add a new parameter who's type will match the category picker renderer defined below
//					configDef.parameterDefinitions.push(
//					{
//					type: "arca:category-picker"
//					});
//					return configDef;
				}
			}

		},

		/**
		 * RENDERERS
		 */

		renderers:
		{

//			"arca:category-picker":
//			{
//			/* Mark this renderer as "manual" in edit mode so no default ui handling is done */
//			manual: 
//			{ 
//			edit: true 
//			},
//			/* Object to save values in */
//			currentCtx: {},
//			edit: function (containerEl, configDef, paramDef, ruleConfig, value)
//			{
//			// Save variables from the last click in the currentCtx object
//			this.renderers["arca:category-picker"].currentCtx =
//			{
//			configDef: configDef,
//			ruleConfig: ruleConfig,
//			paramDef: paramDef
//			};
//
//			// Create a category picker 
//			var picker = new Alfresco.module.ControlWrapper(Alfresco.util.generateDomId());
//			picker.setOptions(
//			{
//			type: "category",
//			container: containerEl,
//			value: (ruleConfig.parameterValues ? ruleConfig.parameterValues["category-value"] : null),
//			controlParams:
//			{
//			multipleSelectMode: false
//			},
//			fnValueChanged:
//			{
//			fn: function(obj)
//			{
//			// When picker is closed make sure we save the values from the picker in the hidden form fields 
//			var ctx = this.renderers["arca:category-picker"].currentCtx;
//			this._setHiddenParameter(ctx.configDef, ctx.ruleConfig, "category-aspect", "cm:generalclassifiable");
//			this._setHiddenParameter(ctx.configDef, ctx.ruleConfig, "category-value", obj.selectedItems[0]);
//			// Re run validations for the form connected to the config component 
//			this._updateSubmitElements(ctx.configDef);
//			},
//			scope: this
//			}
//			});
//			picker.show();
//			}
//			}
		}
			});

		})();