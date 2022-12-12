Ext.define("TreeComboMulti", {
    extend : "Ext.form.field.Picker",
    alias : 'widget.treecombomulti',
    defautText: '',
    labelSeparator: '',
    allowBlank: true,
    triggers: {
  		clear: {
  			cls: 'x-form-clear-trigger', weight: 1, hidden: true,
  			handler: function() {
  				this.reset();
  				this.okFunction();
  				this.getTriggers().clear.hide();
  			}
  		},
    },
    listeners:{
    	expand: function() {
    		var me = this;
    		if (this.picker != undefined) {
    			this.picker.store.root.cascade(function() {
    				this.set('checked', false);
    			})
        		var value = me.getRawValue() == me.defaultText ? '' : me.getRawValue();
        		value = value.replaceAll(' ', '');
        		var arrayId = value.split(',');
        		Ext.each(arrayId, function(item) {
        			me.picker.store.root.cascade(function() {
        				if (this.data.text.replaceAll(' ', '') == item) {
        					this.set('checked', true);
        					me.setParentNodeCustom2(this);
        				}
        			})
        		})
        	}
    	},
    	afterrender: function() {
    		var tree = this;
    		var tooltip = {
				anchor: 'top',
				trackMouse: true,
				html: '',
				listeners : {
					beforeshow: function updateTipBody(tip) {
						tip.update(tree.rawValue);
					}
				}
    		}
    		Ext.create('Ext.tip.ToolTip', Ext.applyIf(tooltip, {target: this.getEl()}));
    	}
    },
    initComponent : function() {
        var self = this;
        Ext.apply(self, {
            fieldLabel : self.allowBlank == true ? self.fieldLabel : (self.fieldLabel == undefined ? '' : self.fieldLabel + '<span style = "color:red;">*</span>'),
            labelWidth : self.labelWidth
        });
        self.callParent();
    },
    createPicker : function() {
        var self = this;
        self.picker = new Ext.tree.Panel({
            height : 400, autoScroll : true, floating : true, resizable: false, focusOnToFront : false, shadow : true, ownerCt : this.ownerCt,
            useArrows : true, store : self.store, root: self.root, anyMatch: true, multiSelect : true, canSelectFolders: true, rootVisible : false,
            dockedItems: [
               {xtype: 'toolbar', dock: 'top', hidden: false, padding: 2, layout: 'fit',
            	   items: [
        	           {xtype: 'metext', emptyText: 'Search...',
							listeners : {
								change: function() {
									store  = this.up('panel').getStore();
									var searchTxt = this.getValue();
									if (searchTxt != '') {					
										var	reSearchTxt = new RegExp(searchTxt, 'i');				
										self.filterCustom(store, reSearchTxt);
									} else {
										self.clearFilterCustom();
									}
								}
							},
						},
					]
		        },
            	{xtype: 'toolbar', dock: 'bottom', width: '30%',
					layout: {
						type: 'hbox',
						pack: 'center'
					},
					items: [
						{xtype : 'mebutton', text : 'OK', padding: '8 10', iconCls: 'fas fa-check btn-icon', 
							handler: function() {
								var records = this.up('panel').getChecked();
								var value = self.defaultText;
								Ext.each(records, function(item) {
									if (item.data.leaf == true) {
										if (value == self.defaultText) {
											value = item.data.text;
										} else {
											value = value + ', ' + item.data.text;
										}
									}
								})
								self.setRawValue(value);
								self.collapse();
								self.okFunction();
								if(value == self.defaultText) {
									self.getTriggers().clear.hide();
								}else {
									self.getTriggers().clear.show();
								}
							}
						},
//						{xtype : 'mebutton', text : 'Remove All', padding: '8 10', iconCls: 'fas fa-times btn-icon', 
//							handler: function() {	
//								self.reset();
//								self.okFunction();
//							}
//						}
					]
            	}
	        ],
            listeners:{
                scope:this,
                itemclick:  function(view, record, item, index, e, eOpts) {
					this.itemTreeClick(view, record, item, index, e, eOpts)
				},
				checkchange: function(node, checked, eOpts) {
					node.set('checked',!checked);
				},
            }
        });
        return self.picker;
    },
    alignPicker : function() {
        // override the original method because otherwise the height of the treepanel would be always 0
        var me = this, picker, isAbove, aboveSfx = '-above';
        if (this.isExpanded) {
            picker = me.getPicker();
            if (me.matchFieldWidth) {
                // Auto the height (it will be constrained by min and max width) unless there are no records to display.
                if (me.bodyEl.getWidth() > this.treeWidth){
                    picker.setWidth(me.bodyEl.getWidth());
                } else picker.setWidth(this.treeWidth);
            }
            if (picker.isFloating()) {
                picker.alignTo(me.inputEl, "", me.pickerOffset);// ""->tl
                // add the {openCls}-above class if the picker was aligned above the field due to hitting the bottom of the viewport
                picker.setXY(picker.getAlignToXY(me.triggerWrap, me.pickerAlign, me.pickerOffset));
                isAbove = picker.el.getY() < me.inputEl.getY();
                me.bodyEl[isAbove ? 'addCls' : 'removeCls'](me.openCls + aboveSfx);
                picker.el[isAbove ? 'addCls' : 'removeCls'](picker.baseCls + aboveSfx);
            }
        }
    },
    itemTreeClick: function(view, record, item, index, e, eOpts) {
    	record.set('checked', !record.get('checked'));
    	var me = this;
    	if (record.data.leaf == false) {
    		if (record.data.checked == true) {
    			record.set('expanded', true);
    		}
    		var checked = record.data.checked;
    		record.cascade( function(){
    			this.set('checked', checked);
    		});
    	}
    	if (record.data.checked == false) {
    		me.setParentNodeCustom(record, false);
    	} else {
    		me.setParentNodeCustom2(record);
    	}
    },
    setParentNodeCustom: function(node, checked) {
    	var me = this;
    	if (node.parentNode != undefined) {
    		node.parentNode.set('checked', checked)
    		me.setParentNodeCustom(node.parentNode, checked);
    	}
    },
    setParentNodeCustom2: function(node) {
    	var me = this;
    	if (node.parentNode != undefined) {
    		var checked = true;
    		Ext.each(node.parentNode.childNodes, function(item) {
    			if (item.data.checked == false) {
    				checked = false;
    			}
    		})
    		node.parentNode.set('checked', checked);
    		me.setParentNodeCustom2(node.parentNode);
    	}
    },
    reset: function() {
    	if (this.picker != undefined) {
	    	var records = this.picker.getChecked();
	    	Ext.each(records, function(item) {
	    		item.set('checked', false);
			})
    	}
    	this.setRawValue(this.defaultText);
    },
    setValue: function (value) {
    	var me = this;
    	if (me.picker == undefined) {
    		me.expand();
    		me.collapse();
    	}
    	me.reset();
    	if(Common.isString(value)) {
    		value = value.replaceAll(' ', '');
    	}
    	var arrayId = Common.isNumber(value) ? [value.toString()] : value.split(',');
    	for (var i=0;i<arrayId.length;i++) {
    		this.picker.store.root.cascade( function(){
    			if (this.data.id.toString() == arrayId[i]){
    				this.set('checked', true);
    				if (this.data.leaf == false) {
    		    		var checked = this.data.checked;
    		    		this.cascade( function(){
    		    			this.set('checked', checked);
    		    		});
    		    	}
    		    	if (this.data.checked == true) {
    		    		me.setParentNodeCustom2(this);
    		    	}
    			}
    		});
    	}
    	var value = me.defaultText;
    	var records = this.picker.getChecked();
		Ext.each(records, function(item) {
			if (item.data.leaf == true) {
				if (value == me.defaultText) {
					value = item.data.text;
				} else {
					value = value + ', ' + item.data.text;
				}
			}
		})
		me.setRawValue(value);
    },
    getValue: function () {
    	var result = '';
    	var me = this;
    	if (this.picker != undefined) {
    		var value = me.getRawValue() == me.defaultText ? '' : me.getRawValue();
    		value = value.replaceAll(' ', '');
    		var arrayId = value.split(',');
    		Ext.each(arrayId, function(item) {
    			me.picker.store.root.cascade(function() {
    				if (this.data.text.replaceAll(' ', '') == item) {
    					if (result == '') {
    						result = this.data.id;
    					} else {
    						result = result + ',' + this.data.id;
    					}
    				}
    			})
    		})
    	}
        return result + '';
    },
    setDefaultText: function(text) {
    	this.defaultText = text;
    	this.setRawValue(this.defaultText);
    },
    clearFilterCustom: function() {
    	this.picker.store.root.cascade(function() {
			this.set('visible', true)
    	});
    },
    filterCustom: function(store, value) {
    	var me = this;
    	store.root.cascade(function() {
    		if (this.data.id != 'root') {
    			this.set('visible', false)
    		}
    	});
    	store.root.cascade(function() {
    		if (this.data.text.match(value) != null) {
    			this.set('visible', true)
    			me.setParentNodeVisibleCustom(this, true);
    		}
    	});
    },
    setParentNodeVisibleCustom: function(node, checked) {
    	var me = this;
    	if (node.parentNode != undefined) {
    		node.parentNode.set('visible', checked)
    		me.setParentNodeVisibleCustom(node.parentNode, checked);
    	}
    },
    getObjectChecked: function(){
    	var result = [];
    	var me = this;
    	if (this.picker != undefined) {
    		var value = me.getRawValue() == me.defaultText ? '' : me.getRawValue();
    		value = value.replaceAll(' ', '');
    		var arrayId = value.split(',');
    		Ext.each(arrayId, function(item) {
    			me.picker.store.root.cascade(function() {
    				if (this.data.text.replaceAll(' ', '') == item) {
    					result.push({
    						id: this.data.id,
    						text: this.data.text
    					});
    				}
    			})
    		})
    	}
        return result;
    },
    okFunction: function() {}
});