Ext.define('TreeComboClass', {
    extend: 'Ext.form.field.Picker',
    alias: 'widget.treecomboclass',
    defautText: '',
	editable: false,
	allowBlank: true,
	multiSelect: true,
	labelSeparator: '',
	store: Ext.widget('metreestore'),
	treeWidth: 300,
	params: {
		year		: new Date().getFullYear(),
		semester	: '01',
		clientCode	: ''
	},
	valueList: [],
	triggers: {
  		clear: {
  			cls: 'x-form-clear-trigger', weight: 1, hidden: true,
  			handler: function() {
  				this.setRawValue(this.defaultText);
  				this.valueList = [];
  				this.reset();
  				this.getTriggers().clear.hide();
  			}
  		},
    },
    emptyText: 'All',
//    onTriggerClick: function (e) {},
    listeners:{
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
	initComponent: function() {
		var self = this;
		Ext.apply(self, {
			fieldLabel: self.allowBlank == true ? self.fieldLabel: self.fieldLabel + '<span style = "color:red;">*</span>',
			labelWidth: self.labelWidth
		});
		self.callParent();
	},
	createPicker: function() {
		var self = this;
		var store = self.store;
		self.picker = new Ext.tree.Panel({
			height: 400, autoScroll: true, floating : true, resizable: false, focusOnToFront : false, shadow : true, ownerCt : this.ownerCt,
            useArrows: true, store: self.store, root: self.root, anyMatch: true, multiSelect : true, canSelectFolders: true, rootVisible : false,
			listeners: {
				scope: this,
				itemclick:  function(view, record, item, index, e, eOpts) {
					this.itemTreeClick(view, record, item, index, e, eOpts)
				},
				checkchange: function(node, checked, eOpts) {
					node.set('checked', !checked);
				},
			}
		});
		self.createDockedItems(self.picker);
		return self.picker;
	},
	alignPicker: function() {
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
				me.bodyEl[isAbove ? 'addCls': 'removeCls'](me.openCls + aboveSfx);
				picker.el[isAbove ? 'addCls': 'removeCls'](picker.baseCls + aboveSfx);
			}
		}
	},
	createDockedItems: function(picker) {
		var self = this;
		Ext.Ajax.request({
		   	url: CONTEXT_PATH + '/general/getLearningYearList.json',
		   	method: 'POST',
			success: function(response){
				if(checkAjaxSessionTimeOut(response)){
					redirectedWhenSessionTimeOut();
				}
				var json = Ext.decode(response.responseText);
				var data = JSON.parse(json.data).data.learningYearList;
				var yearItems = [];
				Ext.each(data, function(item) {
					var year = parseInt(item.learningYearCode);
					yearItems.push({
						itemId		: year,
						text		: item.learningYearName,
						toggleGroup	: 'Y',
						pressed		: year == self.params.year ? true : false,
						listeners: {
    						click: function() {
								self.params.year = year;
								self.loadData();
    						}
    					}
					});
				})
				var dockedItems = [
					{xtype: 'container', dock: 'top', hidden: false, padding: 10, layout: 'hbox', 
						style: 'background-color: #f1f1f1; border: 1px solid #d1d1d1;',
						items: [
							{xtype: 'buttongroup', flex: 1, title: 'Year', margin: '0 5 0 0',
							   	items: yearItems
							},
							{xtype: 'buttongroup', width: 100, title: 'Semester',
							   	items: [
							        {itemId: '01', width: 30, pressed: self.params.semester == '01' ? true : false, text: '01', toggleGroup: 'S',
							        	listeners: {
											click: function() {
												self.params.semester = '01';
												self.loadData();
											}
										}
							        },
							        {itemId: '02', width: 30, pressed: self.params.semester == '02' ? true : false, text: '02', toggleGroup: 'S',
							        	listeners: {
											click: function() {
												self.params.semester = '02';
												self.loadData();
											}
										}
							        },
							        {itemId: '03', width: 30, pressed: self.params.semester == '03' ? true : false, text: '03', toggleGroup: 'S',
								        	listeners: {
												click: function() {
													self.params.semester = '03';
													self.loadData();
												}
											}
								        }
						        ]
							}
			        	]
					}
				]
				if(self.multiSelect) {
					dockedItems.push({
						xtype: 'toolbar', dock: 'bottom', width: '30%',
						layout: {
							type: 'hbox',
							pack: 'center'
						},
						items: [
							{xtype: 'mebutton', text: 'OK', padding: '8 10', iconCls: 'fas fa-check btn-icon', 
								handler: function() {
									var records = this.up('panel').getChecked();
									self.okClick(records);
								}
							}
						]
					})
				}
				picker.addDocked(dockedItems);
				self.loadData();
			}
		});
	},
	okClick: function(records) {
		var self = this;
		var valueList = [];
		var rawValue = [];
		
		Ext.each(records, function(item) {
			if(item.data.leaf == true) {
				valueList.push({
					code: item.data.id,
					name: item.data.text
				})
			}
		})
		Ext.each(valueList, function(item) {
			rawValue.push(item.name);
		})
		self.setRawValue(rawValue.toString().replaceAll(',', ', '));
		self.valueList = valueList;
		self.collapse();
		if(valueList.length > 0) {
			this.getTriggers().clear.show();
		}
	},
	loadData: function() {
		var self = this,
			store = self.store,
			params = self.params,
			multiSelect = self.multiSelect;
		var params = {
			learningYearCode	: params.year,
			semesterGbn			: params.semester,
			clientCode			: params.clientCode
		}
		self.reset();
		Ext.Ajax.request({
		   	url:  CONTEXT_PATH + '/general/getTreeClassList.json',
		   	params : params,
			success: function(response){
				if(checkAjaxSessionTimeOut(response)){
					redirectedWhenSessionTimeOut();
				}
				var data = JSON.parse(JSON.parse(response.responseText).data).data,
					courseList = data.courseList,
					classList = data.classList,
					parentList = [];
				if(courseList.length == 0) {
					store.setRoot({});
					return;
				}
				Ext.each(courseList, function(item) {
					var course = _.find(classList, {courseCode: item.courseCode.toString()});
					if(course != null) {
						parentList.push(item);
					}
				})
				
				var childs = [];
				Ext.each(parentList, function(item) {
					var classChilds = _.filter(classList, {courseCode: item.courseCode + ''}),
						children = [];
					Ext.each(classChilds, function(record) {
						children.push({
							id			: record.classCode, 
							text		: record.className, 
							leaf		: true,
							checked		: multiSelect == true ? false : null,
						})
					})
					childs.push({
						id			: item.courseCode, 
						text		: item.courseName, 
						leaf		: false, 
						children	: children,
						checked		: multiSelect == true ? false : null,
					})
				})
				
				store.setRoot({
					text: 'Root',
					id: 'root',
					expanded: true,
					checked: false,
					children: childs
				});
			}
		});
	},
	itemTreeClick: function(view, record, item, index, e, eOpts) {
		var self = this;
		if(self.multiSelect == false) {
			if(record.data.leaf == true) {
				self.setRawValue(record.data.text);
				self.rawValue = record.data.text;
				self.collapse();
				self.valueList = [];
				self.valueList.push({
					code	: record.data.id,
					name	: record.data.text
				});
				self.okFunction();
			}
		}else {
			record.set('checked', !record.get('checked'));
	    	if(record.data.leaf == false) {
	    		if(record.data.checked == true) {
	    			record.set('expanded', true);
	    		}
	    		var checked = record.data.checked;
	    		record.cascade( function(){
	    			this.set('checked', checked);
	    		});
	    	}
	    	if(record.data.checked == false) {
	    		self.setParentNodeCustom(record, false);
	    	}else {
	    		self.setParentNodeCustom2(record);
	    	}
		}
		self.validate();
	},
	setParentNodeCustom: function(node, checked) {
    	var self = this;
    	if (node.parentNode != undefined) {
    		node.parentNode.set('checked', checked)
    		self.setParentNodeCustom(node.parentNode, checked);
    	}
    },
    setParentNodeCustom2: function(node) {
    	var self = this;
    	if (node.parentNode != undefined) {
    		var checked = true;
    		Ext.each(node.parentNode.childNodes, function(item) {
    			if (item.data.checked == false) {
    				checked = false;
    			}
    		})
    		node.parentNode.set('checked', checked);
    		self.setParentNodeCustom2(node.parentNode);
    	}
    },
    okFunction: function() {},
	reset: function() {
		this.valueList = [];
		this.setRawValue(this.defaultText);
		this.rawValue = '';
		if (this.picker != undefined) {
	    	var records = this.picker.getChecked();
	    	Ext.each(records, function(item) {
	    		item.set('checked', false);
			})
    	}
	},
	getValue: function() {
		var values = [];
		Ext.each(this.valueList, function(item) {
			values.push(item.code)
		})
		return values.toString();
	},
	getRawValue: function () {
		var values = [];
		Ext.each(this.valueList, function(item) {
			values.push(item.name)
		})
		return values.toString().replaceAll(',', ', ');
	},
});