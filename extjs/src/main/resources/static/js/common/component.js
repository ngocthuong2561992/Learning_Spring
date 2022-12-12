Ext.define('component.MeFormPanel', {
	extend : 'Ext.form.Panel',
	alias : 'widget.meform',
	width: '100%',
	border: false,
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.MePanelPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.mepanel',
	width: '100%',
	border: false,
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.MeFieldSet', {
	extend : 'Ext.form.FieldSet',
	alias : 'widget.mefieldset',
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.MeContainer', {
	extend : 'Ext.container.Container',
	alias : 'widget.mecontainer',
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.MeText', {
	extend : 'Ext.form.field.Text',
	labelSeparator: '',
	alias : 'widget.metext',
    width : "100%",
	initComponent : function() {
		var me = this;
        this.afterLabelTextTpl = new Ext.XTemplate(
            '<tpl if="this.allowBlank()">',
                '<span style = "color:red;">*</span>',
            '</tpl>',
            {disableFormats: true,
                allowBlank: function(){
                   return me.allowBlank === false;
                }
            }
        );
		this.callParent(arguments);
	}
});

Ext.define('component.MeDate', {
	extend : 'Ext.form.field.Date',
	alias : 'widget.medate',
	labelSeparator: '',
	format : 'Y/m/d',
	initComponent : function() {
		var me = this;
        this.afterLabelTextTpl = new Ext.XTemplate(
            '<tpl if="this.allowBlank()">',
                '<span style = "color:red;">*</span>',
            '</tpl>',
            {disableFormats: true,
                allowBlank: function(){
                   return me.allowBlank === false;
                }
            }
        );
		this.callParent(arguments);
	}
});

Ext.define('component.MeComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.mecombo',
	labelSeparator: '',
    width : "100%",
	initComponent : function() {
		var me = this;
        this.afterLabelTextTpl = new Ext.XTemplate(
            '<tpl if="this.allowBlank()">',
                '<span style = "color:red;">*</span>',
            '</tpl>',
            {disableFormats: true,
                allowBlank: function(){
                   return me.allowBlank === false;
                }
            }
        );
		this.callParent(arguments);
	},
	checkChangeEvents : Ext.isIE ? 
        ['change', 'propertychange', 'keyup'] :
        ['change', 'input', 'textInput', 'keyup', 'dragdrop']
});

Ext.define('component.MeTextArea', {
	extend : 'Ext.form.TextArea',
	alias : 'widget.mearea',
	labelSeparator: '',
	initComponent : function() {
		var me = this;
        this.afterLabelTextTpl = new Ext.XTemplate(
            '<tpl if="this.allowBlank()">',
                '<span style = "color:red;">*</span>',
            '</tpl>',
            {disableFormats: true,
                allowBlank: function(){
                   return me.allowBlank === false;
                }
            }
        );
		this.callParent(arguments);
	}
});

Ext.define('component.MeDisplayfield', {
	extend : 'Ext.form.field.Display',
	alias : 'widget.medisplayfield',
	labelSeparator: '',
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.MeRadioGroup', {
	extend : 'Ext.form.RadioGroup',
	alias : 'widget.meradiogroup',
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.MeNumber', {
	extend : 'Ext.form.field.Number',
	alias : 'widget.menumber',
	labelSeparator: '',
	width: '100%',
	hideTrigger: true,
	keyNavEnabled: false,
    mouseWheelEnabled: false,
//    decimalPrecision : 0,
	initComponent : function() {
		var me = this;
        this.afterLabelTextTpl = new Ext.XTemplate(
            '<tpl if="this.allowBlank()">',
                '<span style = "color:red;">*</span>',
            '</tpl>',
            {disableFormats: true,
                allowBlank: function(){
                   return me.allowBlank === false;
                }
            }
        );
		this.callParent(arguments);
	}
});

Ext.define('component.MeNumber', {
	extend : 'Ext.form.field.Number',
	alias : 'widget.menumber2',
	labelSeparator: '',
	width: '100%',
	hideTrigger: true,
	keyNavEnabled: false,
    mouseWheelEnabled: false,
	initComponent : function() {
		var me = this;
        this.afterLabelTextTpl = new Ext.XTemplate(
            '<tpl if="this.allowBlank()">',
            '</tpl>',
            {disableFormats: true,
                allowBlank: function(){
                   return me.allowBlank === false;
                }
            }
        );
		this.callParent(arguments);
	}
});

Ext.define('component.meCheckbox', {
	extend : 'Ext.form.field.Checkbox',
	alias : 'widget.mecheckbox',
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.MeCheckboxGroup', {
	extend : 'Ext.form.CheckboxGroup',
	alias : 'widget.mecheckboxgroup',
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.meMoney', {
	extend : 'Ext.form.field.Text',
	alias : 'widget.memoney',
	width: '100%',
	labelSeparator: '',
	enableKeyEvents: true,
	maskRe : /[0-9,]/,
	validateOnBlur : false, validateOnChange : true,
   	validator : function(value) {
    	var value = value;
    	var newValue = '';
		value = value.toString().replaceAll(/[^0-9]/g, '');
		newValue = Ext.util.Format.number(value, '0,000');
		this.setValue(newValue);
    	return true;
   	},
	initComponent : function() {
		var me = this;
        this.afterLabelTextTpl = new Ext.XTemplate(
            '<tpl if="this.allowBlank()">',
                '<span style = "color:red;">*</span>',
            '</tpl>',
            {disableFormats: true,
                allowBlank: function(){
                   return me.allowBlank === false;
                }
            }
        );
		this.callParent(arguments);
	}
});

Ext.define('component.mePhone', {
	extend : 'Ext.form.field.Text',
	alias : 'widget.mephone',
	width: '100%',
	labelSeparator: '',
	enableKeyEvents: true,
	maskRe : /[0-9.]/,
	validateOnBlur : false, validateOnChange : true,
   	validator : function(value) {
   		var newValue = '';
   		value = value.toString().replaceAll(/[^0-9]/g, '');
		if(value.length < 4) {
			newValue = value;
		}else if(value.length == 4) {
			newValue = value.substr(0, 4) + '-';
		}else if(value.length < 7) {
			newValue = value.substr(0, 4) + '-' + value.substr(4, value.length - 4);
		}else if(value.length == 7) {
			newValue = value.substr(0, 4) + '-' + value.substr(4, 3) + '-';
		}else if(value.length <= 11) {
			newValue = value.substr(0, 4) + '-' + value.substr(4, 3) + '-' + value.substr(7, value.length - 7);
		}else {
			newValue = value.substr(0, 4) + '-' + value.substr(4, 3) + '-' + value.substr(7, 4);
		}
    	this.setValue(newValue);
   		if((value.length > 0 && value.length < 10) || value.length > 11) 
   			return 'Phone Number must be 10 or 11 digit';
   		else 
   			return true;
   	},
   	listeners:  {
   		keydown: function(f, e) {
        	var lastValue = f.lastValue;
        	if(e.getKey() == e.BACKSPACE && lastValue.length > 0) {
            	if(lastValue.charAt(e.target.selectionEnd - 1) == '-') {
            		var position = e.target.selectionEnd;
            		lastValue = lastValue.replaceAt(position - 2, '-');
            		f.setValue(lastValue);
            		f.selectText(position - 2, position - 2);
            		e.preventDefault();
            	}
            }
        }
    },
	initComponent : function() {
		var me = this;
        this.afterLabelTextTpl = new Ext.XTemplate(
            '<tpl if="this.allowBlank()">',
                '<span style = "color:red;">*</span>',
            '</tpl>',
            {disableFormats: true,
                allowBlank: function(){
                   return me.allowBlank === false;
                }
            }
        );
		this.callParent(arguments);
	}
});

Ext.define('component.meTime', {
	extend : 'Ext.form.field.Text',
	alias : 'widget.metime',
	width: '100%',
	labelSeparator: '',
	enableKeyEvents: true,
	maskRe : /[0-9.]/,
	validateOnBlur : false, validateOnChange : true,
   	validator : function(value) {
   		var newValue = '';
   		value = value.toString().replaceAll(/[^0-9]/g, '');
		if(value.length < 2) {
			newValue = value;
		}else if(value.length == 2) {
			newValue = value.substr(0, 2) + ':';
		}else if(value.length < 4) {
			newValue = value.substr(0, 2) + ':' + value.substr(2, value.length - 2);
		}else if(value.length >= 4) {
			newValue = value.substr(0, 2) + ':' + value.substr(2, 2);
		}
    	this.setValue(newValue);
    	var check = true;
    	if(value.length == 4) {
    		var hours = parseInt(value.substr(0, 2));
    		var minute = parseInt(value.substr(2, 2));
    		if(hours > 23 || minute > 59) {
    			check = false;
    		}
    	}
   		if((value.length > 0 && value.length < 4) || check == false) 
   			return 'Time must be HH:mm';
   		else 
   			return true;
   	},
   	listeners:  {
   		keydown: function(f, e) {
        	var lastValue = f.lastValue;
        	if(e.getKey() == e.BACKSPACE && lastValue.length > 0) {
            	if(lastValue.charAt(e.target.selectionEnd - 1) == ':') {
            		var position = e.target.selectionEnd;
            		lastValue = lastValue.replaceAt(position - 2, '-');
            		f.setValue(lastValue);
            		f.selectText(position - 2, position - 2);
            		e.preventDefault();
            	}
            }
        }
    },
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.MeButton', {
	extend : 'Ext.button.Button',
	alias : 'widget.mebutton',
	initComponent : function() {
		this.addClsWithUI(this.overCls);
		this.callParent(arguments);
	},
	onMouseLeave : function(e) {
	}
});

Ext.define('component.meDataStore', {
	extend : 'Ext.data.Store',
	alias : 'widget.meDataStore',
	listeners:{
        load:function(store, record, success, opts){
        	 handleCheckSessionLoadStore(store, record, success, opts);
        }
    },
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.meStore', {
	extend : 'Ext.data.Store',
	alias : 'widget.mestore',
	listeners: {
        load: async function(store, record, success, opts){
			try {
				await handleCheckSessionLoadStore(store, record, success, opts);
				let data = Ext.decode(opts.getResponse().responseText);
				if(data.actionStatus == 'error') {
					showMessageBoxError(data.message);
				}
			}catch(e) {

			}
        }
    },
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.meTreeStore', {
	extend : 'Ext.data.TreeStore',
	alias : 'widget.metreestore',
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.MeGridPanel', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.megrid',
	cls: 'wrapper-table',
	bodyCls: 'wrapper-table-body tb-cell tb-cell-center',
	layout:{
        type:'fit',
        align:'stretch',
        pack:'start'
	},
	border: false,
    header: false,
	autoScroll : true,
	autoHeight : true,
    stripeRows : true,
    collapsible : true,
    preserveScrollOnRefresh : true,
    enableColumnMove  :true,
    enableColumnResize:true,
	width : "100%",
	minHeight: 100,
    viewConfig : {
        forceFit : true
    },
	initComponent : function() {
		this.callParent(arguments);
	}
});

Ext.define('component.MeButton', {
	extend : 'Ext.button.Button',
	alias : 'widget.mebutton1',
	initComponent : function() {
		var me = this,
        localized = me.localized,
        value;
	    if (Ext.isObject(localized)) {
	        for (var prop in localized) {
	            value = localized[prop];
	            if (value && value != '') {
	                me[prop] = eval(value) == undefined ? 'Undefined' : eval(value);
	            }
	        }
	    }
	    me.callParent(arguments);
	},
});

Ext.define('component.MeWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.mewindow',
	layout: 'fit',
	width: 600,
	closeAction: 'hide',
	overflowY : 'hidden',
	overflowX : 'hidden',
	modal: true,
	maximizable : true,
	resizable : true,
	resizeHandles: 'w e',
	autoScroll: true,
	style: 'box-shadow: 0 0 16px rgba(0, 0, 0, 0.12), 0 16px 16px rgba(0, 0, 0, 0.24) !important;',
	bodyStyle: 'background: white;',
	listeners: {
	    'show': function() {
	    	if(this.lastBox.height > Ext.getBody().getViewSize().height) {
	    		this.setHeight(Ext.getBody().getViewSize().height);
	    	}
	    	
//            var dom = Ext.dom.Query.select('.x-mask');
//            Ext.each(dom, function(item) {
//            	var el = Ext.get(item);
//            	el.addCls('customWindow-Mask');
//            });
            if(this.defaultWidth != undefined) {
            	this.setWidth(this.defaultWidth);
            }
            this.center();
	    },
	    'hide': function() {
	    	
	    },
	},
	initComponent : function() {
		var me = this;
	    me.callParent(arguments);
//	    me.mon(Ext.getBody(), 'click', function(el, e) {
//            me.close(me.closeAction);
//        }, me, {
//            delegate: '.x-mask'
//        });
	}
});
Ext.define('component.MeModal', {
	extend: 'Ext.window.Window',
	alias: 'widget.memodal',
	header: false,
    cls: 'modal-wrapper',
    bodyCls: 'modal-wrapper-inner-body ',
    resizable: false,
    draggable: false,
    autoHeight : true,
    //closeAction: 'hide',
	overflowY : 'hidden',
	overflowX : 'hidden',
    modal : true,
    width: "100%",
    layout: 'fit',
	style: 'box-shadow: 0 0 16px rgba(0, 0, 0, 0.12), 0 16px 16px rgba(0, 0, 0, 0.24) !important;',
	listeners: {
	    'show': function() {
	    	if(this.getHeight > Ext.getBody().getViewSize().height) {
	    		this.setHeight(Ext.getBody().getViewSize().height);
	    	}
	    	
            var dom = Ext.dom.Query.select('.x-mask');
            Ext.each(dom, function(item) {
            	var el = Ext.get(item);
            	el.addCls('customWindow-Mask');
            });
            this.center();
//		    Ext.getCmp('PLC-mainPanelID').getEl().mask();
	    },
	    'hide': function() {
            var dom = Ext.dom.Query.select('.x-mask');
            Ext.each(dom, function(item) {
            	var el = Ext.get(item);
            	el.removeCls('customWindow-Mask');
            });
	    },
	},
	initComponent : function() {
		var me = this;
	    me.callParent(arguments);
	}
});

Ext.define('Ext.ux.form.MultiFile', {
    extend: 'Ext.form.field.File',
    alias: 'widget.multifilefield',
    initComponent: function () {
        var me = this;
        me.on('render', function () {
            me.fileInputEl.set({ multiple: 'multiple' });
        });
        me.callParent(arguments);
    },
    onFileChange: function (button, e, value) {
        var me = this,
            upload = me.fileInputEl.dom,
            files = upload.files,
            names = [];
 
        if (files) {
            for (var i = 0; i < files.length; i++)
                names.push(files[i].name);
            value = names.join(', ');
        }
        me.callParent(arguments);
    }
});

