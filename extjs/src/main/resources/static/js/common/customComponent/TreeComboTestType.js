Ext.define("TreeComboTestType", {
    extend : "Ext.form.field.Picker",
    alias : 'widget.treecombotesttype',
    defautText: '',
    statisticYn: false,
	value: '',
	rawValue: '',
	rootVisibleCustom: true,
	allowBlank: true,
	labelSeparator: '',
	initComponent : function() {
		var self = this;
		Ext.apply(self, {
			fieldLabel : self.allowBlank == true ? self.fieldLabel : self.fieldLabel + '<span style = "color:red;">*</span>',
			labelWidth : self.labelWidth
		});
		self.callParent();
	},
	createPicker : function() {
		var self = this;
		self.picker = new Ext.tree.Panel({
			height : 400,
			autoScroll : true,
			floating : true,
			resizable: false,
			focusOnToFront : false,
			shadow : true,
			ownerCt : this.ownerCt,
			useArrows : true,
			store : self.store,
			root: self.root,
			anyMatch: true,
			multiSelect : false,
			canSelectFolders: true,
			rootVisible : self.rootVisibleCustom,
			listeners:{
				scope:this,
				itemclick:  function(view, record, item, index, e, eOpts) {
					this.itemTreeClick(view, record, item, index, e, eOpts)
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
		var me = this;
		if (record.data.leaf == true) {
			if (this.statisticYn == false) {
				me.setRawValue(record.parentNode.data.text + ' > ' + record.data.text);
			} else {
				me.setRawValue(record.data.text);
			}
			me.rawValue = record.data.text;
			me.value = record.data.id;
			me.collapse();
		} else if (record.data.id == '0') {
			me.setRawValue(record.data.text);
			me.rawValue = '';
			me.value = 0;
			me.collapse();
		}
		me.select(me.value);
	},
	select: function(value) {},
	reset: function() {
		this.getPicker().getSelectionModel().select(0);
    	this.setRawValue(this.defaultText);
		this.value = 0;
	},
	setValue: function (value) {
		var me = this;
		if (me.store.root != undefined) {
			if (me.picker == undefined) {
				me.expand();
				me.collapse();
			}
			me.reset();
			me.store.root.cascade(function() {
				if (this.data.id == value) {
					me.value = value;
					if (this.data.leaf == true) {
						if (this.statisticYn == false) {
							me.setRawValue(this.parentNode.data.text + ' > ' + this.data.text);
						} else {
							me.setRawValue(this.data.text);
						}
						me.rawValue = this.data.text;
					} else {
						me.setRawValue(this.data.text);
						me.rawValue = this.data.text;
					}
				}
			})
		}
	},
	getValue: function () {
		return this.value;
	},
	getRawValue: function () {
		return this.rawValue;
	},
	setDefaultText: function(text) {
		this.defaultText = text;
		this.setRawValue(this.defaultText);
	},
});