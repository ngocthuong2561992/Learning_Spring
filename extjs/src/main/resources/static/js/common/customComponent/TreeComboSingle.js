/*
 Tree combo
 Use with 'Ext.data.TreeStore'
 
 
 ASDASDASDASD
 
 
 

 If store root note has 'checked' property tree combo becomes multiSelect combo (tree store must have records with 'checked' property)

 Has event 'itemclick' that can be used to capture click

 Options:
 selectChildren - if set true and if store isn't multiSelect, clicking on an non-leaf node selects all it's children
 canSelectFolders - if set true and store isn't multiSelect clicking on a folder selects that folder also as a value

 Use:

 single leaf node selector:
 selectChildren: false
 canSelectFolders: false
 - this will select only leaf nodes and will not allow selecting non-leaf nodes

 single node selector (can select leaf and non-leaf nodes)
 selectChildren: false
 canSelectFolders: true
 - this will select single value either leaf or non-leaf

 children selector:
 selectChildren: true
 canSelectFolders: true
 - clicking on a node will select it's children and node, clicking on a leaf node will select only that node

 This config:
 selectChildren: true
 canSelectFolders: false
 - is invalid, you cannot select children without node

 */
//Ext.define('Ext.ux.TreeCombo', {
Ext.define('ext.common.extjs.ux.TreeComboSingle', {
    extend: 'Ext.form.field.Picker',
    alias: 'widget.treecombo',

    requires:[
        'Ext.util.DelayedTask',
        'Ext.util.Filter'
    ],

    mixins: [
       'Ext.util.StoreHolder'
    ],

    records: [],
    recursiveRecords: [],
    testTypeCombo : false,
    valueSingleSelectId : 0,
    valueSingleSelectName : '',
    selectChildren: false, //true,
    canSelectFolders: false,// true,
    multiSelect: false, //true, 
    displayField: 'text',
    valueField: 'id',
    afterLoadSetValue: false,
    readOnly: false,

    enableRegEx: true,

    queryMode: 'local',

    /**
     * @cfg {Boolean} [anyMatch=false]
     * Configure as `true` to allow matching of the typed characters at any position in the {@link #valueField}'s value.
     */
    anyMatch: true,

    /**
     * @cfg {Boolean} [caseSensitive=false]
     * Configure as `true` to make the filtering match with exact case matching
     */
    caseSensitive: false,

    /**
     * @cfg {Boolean} typeAhead
     * `true` to populate and autoselect the remainder of the text being typed after a configurable delay
     * ({@link #typeAheadDelay}) if it matches a known value.
     */
    typeAhead: false,

    /**
     * @cfg {Number} typeAheadDelay
     * The length of time in milliseconds to wait until the typeahead text is displayed if `{@link #typeAhead} = true`
     */
    typeAheadDelay: 250,

    /**
     * @cfg {String} triggerAction
     * The action to execute when the trigger is clicked.
     *
     *   - **`'all'`** :
     *
     *     {@link #doQuery run the query} specified by the `{@link #allQuery}` config option
     *
     *   - **`'last'`** :
     *
     *     {@link #doQuery run the query} using the `{@link #lastQuery last query value}`.
     *
     *   - **`'query'`** :
     *
     *     {@link #doQuery run the query} using the {@link Ext.form.field.Base#getRawValue raw value}.
     *
     * See also `{@link #queryParam}`.
     */
    triggerAction: 'all',


    initComponent: function () {
        var me = this,
            isDefined = Ext.isDefined,
            isLocalMode;

        isLocalMode = me.queryMode === 'local';
        if (!isDefined(me.queryDelay)) {
            me.queryDelay = isLocalMode ? 10 : 500;
        }
        if (!isDefined(me.minChars)) {
            me.minChars = isLocalMode ? 0 : 4;
        }

        me.doQueryTask = new Ext.util.DelayedTask(me.doRawQuery, me);

        me.callParent();
        
        if(me.testTypeCombo){
        	me.valueSingleSelectName = 'All';
    		me.valueSingleSelectId = 0;
			me.setRawValue(me.valueSingleSelectName);
			me.value = 0;
        }
        
//        me.createPicker();

    },

    recursivePush: function (node) {
        var me = this;
        me.recursiveRecords.push(node);

        node.eachChild(function (nodesingle) {
            if (nodesingle.hasChildNodes() == true) {
                me.recursivePush(nodesingle);
            }
            else me.recursiveRecords.push(nodesingle);
        });
    },
    recursiveUnPush: function (node) {
        var me = this;
        Ext.Array.remove(me.records, node);

        node.eachChild(function (nodesingle) {
            if (nodesingle.hasChildNodes() == true) {
                me.recursiveUnPush(nodesingle);
            }
            else Ext.Array.remove(me.records, nodesingle);
        });
    },

    setValue: function (valueInit) {
        if (typeof valueInit == 'undefined') return;

        var me = this,
            tree = this.getPicker(),
            value = valueInit.split(',');

        inputEl = me.inputEl;

        if (tree.store.isLoading()) {
            me.afterLoadSetValue = valueInit;
        }

        if (inputEl && me.emptyText && !Ext.isEmpty(value)) {
            inputEl.removeCls(me.emptyCls);
        }

        if (tree == false) return false;

        var node = tree.getRootNode();
        if (node == null) return false;

        me.recursiveRecords = [];
        me.recursivePush(node);

        var valueFin = [];
        var idsFin = [];

        if (me.multiSelect == true) {
            Ext.each(me.recursiveRecords, function (record) {
                record.set('checked', false);
            });
        }

        me.records = [];
        if(me.testTypeCombo){
        	var parentId = 0,
        	parentName,
        	valueName,
        	rootNode = false;
        	Ext.each(me.recursiveRecords, function (record) {
                var data = record.get(me.valueField);
                Ext.each(value, function (val) {
                    if (data == val) {
                    	if (record.get('parentId') != 0){
                    		if (val == 0 ){
                        		rootNode = true;
                        		me.valueSingleSelectName = 'All';
                        		me.valueSingleSelectId = 0;
                        	}else{
                        		rootNode = false;
                        		me.valueSingleSelectName = record.get(me.displayField);
                        		me.valueSingleSelectId =  record.get(me.valueField);
                        	}
                        	parentId = record.get('parentId');
                        	valueName = record.get(me.displayField);
                            valueFin.push(record.get(me.displayField));
                            idsFin.push(data);
                            
                            if (me.multiSelect == true) record.set('checked', true);
                            me.records.push(record);
                    	}
                    }
                });
            });
        	if(parentId != 0){
        		Ext.each(me.recursiveRecords, function (record) {
                    var data = record.get(me.valueField);
                    Ext.each(value, function (val) {
                        if (data == parentId) {
                        	parentName = record.get(me.displayField);
                        }
                    });
                });
            	me.value = valueInit;
            	if(rootNode){
            		 me.setRawValue('All');
            		 
            	}else{
            		 me.setRawValue(parentName +'>'+valueName);
            	}
        	}
        }else{
        	 Ext.each(me.recursiveRecords, function (record) {
                 var data = record.get(me.valueField);
                 Ext.each(value, function (val) {
                     if (data == val) {
                         valueFin.push(record.get(me.displayField));
                         idsFin.push(data);
                         if (me.multiSelect == true) record.set('checked', true);
                         me.records.push(record);
                     }
                 });
             });

             me.value = valueInit;
             me.setRawValue(valueFin.join(', '));
        }
       
        me.checkChange();
        
        return me;
    },
    getValue: function () {
        return this.value;
    },
    getSubmitValue: function () {
        return this.value;
    },
    checkParentNodes: function (node) {
        if (node == null) return;

        var me = this,
            checkedAll = true,
            ids = [];

        Ext.each(me.records, function (value) {
            ids.push(value.get(me.valueField));
        });

        node.eachChild(function (nodesingle) {
            if (!Ext.Array.contains(ids, nodesingle.get(me.valueField))) checkedAll = false;
        });

        if (checkedAll == true) {
            me.records.push(node);
            me.checkParentNodes(node.parentNode);
        }
        else {
            Ext.Array.remove(me.records, node);
            me.checkParentNodes(node.parentNode);
        }
    },

    createPicker: function () {    	
        var me = this,
            picker,
            pickerCfg =  Ext.apply({
				xtype: 'treepanel',
				hidden: true,
				//minHeight: 300,
				height : 400,
				width: 200,
				rootVisible: true,
				floating: true,
			//	useArrows: true,
				store: me.store,
				dockedItems: [
			        {
						xtype: 'toolbar',
						dock: 'top',
						items: [
							{
								xtype: 'textfield',
/*								id: 'treeCombo_searchFieldId',
								name:'treeCombo_searchField',*/ //should remove name, id of this textfield,otherwise it cannot be used by multiple pages - Helen
								height : 30,
								width: '100%',
								listeners : {
									change: function() {
										var searchTxt = this.getValue(),
										//store  = Ext.getCmp('workHabitMgt_courseLevelMapComboId').getStore();
										store  = this.up('panel').getStore();
										if (searchTxt != '') {					
											//ig: i - insentive-case & g - global to search the whole string rather than just find the first occurrence
											var	reSearchTxt = new RegExp(searchTxt, 'i');				
											store.filter('text', reSearchTxt);		
										} else {
											//store.filter('text', '');
											store.clearFilter();
										}
									}
								},
							},
						]
			        },
//			        {
//			        	xtype: 'toolbar',
//						dock: 'bottom',
//						layout: {
//							//type: 'hbox',
//							//align: 'middle',
//							pack: 'center'
//						},
//						width: '30%',
//						items: [ 
//							{
//								xtype : 'button',
//								text : '선택 항목 확인',
//								width: '30%',
//								height: 30,
//								cls:'x-btn-default-small',
//								handler: function() {					
//									Ext.MessageBox.alert('선택 항목', this.up('panel').up('field').getRawValue(), function(){
//										return true;
//									});
//									
//									var records = this.up('panel').getChecked();
//									names = [];
//									Ext.Array.each(records, function(rec){
//										names.push(rec.get('text'));
//									});
//									Ext.MessageBox.show({
//										title: '선택한 항목',
//										msg: names.join('<br />'),
//										icon: Ext.MessageBox.INFO
//									});									
//								}
//							}
//						]
//			        },
				],
				/*tbar: [
					{
						xtype: 'textfield',
						id: 'treeCombo_searchFieldId',
						name:'treeCombo_searchField',
						height : 30,
						width: '60%',
						listeners : {
							change: function() {
								var searchTxt = this.getValue(),
								//store  = Ext.getCmp('workHabitMgt_courseLevelMapComboId').getStore();
								store  = this.up('panel').getStore();
								if (searchTxt != '') {					
									//ig: i - insentive-case & g - global to search the whole string rather than just find the first occurrence
									var	reSearchTxt = new RegExp(searchTxt, 'ig');				
									store.filter('text', reSearchTxt);		
								} else {
									store.filter('text', '');	
								}
							}
						},
					},
					{
						xtype : 'button',
						text : '선택 항목 확인',
						width: '40%',
						height: 30,
						cls:'x-btn-default-small',
						handler: function() {
							//alert("선택 항목::" + this.up('panel').up('field').getRawValue());							
							Ext.MessageBox.alert('선택 항목', this.up('panel').up('field').getRawValue(), function(){
								return true;
							});
						}
					}
				]*/
            }, me.treeConfig);

        picker = me.picker = Ext.widget(pickerCfg);
        picker.getStore().filter('text', ''); //Helen: to remove the error when click the root
        picker.getStore().clearFilter();
        picker.on({
            scope: me,
            load: me.onPickerLoad,
            itemclick: me.onItemClick,
            checkchange:me.oncheckchange //helen
        });
        if (me.picker.getRootNode().get('checked') != null) me.multiSelect = true; 
        return picker;
    },
    onPickerLoad: function (store, records) {
        var me = this;        
        if (me.afterLoadSetValue != false) {
            me.setValue(me.afterLoadSetValue);
        }
        me.picker.getStore().clearFilter(); //Helen - to avoid confict among pages using this TreeCombo when filtering.
    },
    onItemClick: function (view, record, item, index, e, eOpts) {
        var me = this,
            values = [];

        var node = me.picker.getRootNode().findChild('id', record.get(me.valueField), true);
        if (node == null) {
            if (me.picker.getRootNode().get(me.valueField) == record.get(me.valueField)) node = me.picker.getRootNode();
            else return false;
        }

        if (me.multiSelect == false) me.records = [];

        if (me.canSelectFolders == false && record.get('leaf') == false) return false;
        if (record.get('leaf') == true || me.selectChildren == false) {
            if (me.multiSelect == false) me.records.push(record);
            else {
                if (record.get('checked') == false) me.records.push(record);
                else Ext.Array.remove(me.records, record);
            }
        }
        else {
            me.recursiveRecords = [];
            if (me.multiSelect == false || record.get('checked') == false) {
                me.recursivePush(node);
                Ext.each(me.recursiveRecords, function (value) {
                    if (!Ext.Array.contains(me.records, value)) me.records.push(value);
                });
            }
            else if (record.get('checked') == true) {
                me.recursiveUnPush(node);
            }
        }
        if (!me.testTypeCombo){
        	 if (me.canSelectFolders == true) me.checkParentNodes(node.parentNode);
        }
        Ext.each(me.records, function (record) {
            values.push(record.get(me.valueField));
        });
        me.setValue(values.join(','));
        me.fireEvent('itemclick', me, record, item, index, e, eOpts, me.records, values);
        if (me.multiSelect == false) me.onTriggerClick();
    },

    oncheckchange : function(node, checked , opt) {
    	//hack for itemclick event - helen                                    
    	node.set("checked", !checked);
    },

    onFieldMutation: function(e) {
        var me = this,
            key = e.getKey(),
            isDelete = key === e.BACKSPACE || key === e.DELETE,
            rawValue = me.inputEl.dom.value,
            len = rawValue.length;

        // Do not process two events for the same mutation.
        // For example an input event followed by the keyup that caused it.
        // We must process delete keyups.
        // Also, do not process TAB event which fires on arrival.
        if (!me.readOnly && (rawValue !== me.lastMutatedValue || isDelete) && key !== e.TAB) {
            me.lastMutatedValue = rawValue;
            me.lastKey = key;
            if (len && (e.type !== 'keyup' || (!e.isSpecialKey() || isDelete))) {
                me.doQueryTask.delay(me.queryDelay);
            } else {
                // We have *erased* back to empty if key is a delete, or it is a non-key event (cut/copy)
                if (!len && (!key || isDelete)) {
                    // Essentially a silent setValue.
                    // Clear our value, and the tplData used to construct a mathing raw value.
                    if (!me.multiSelect) {
                        me.value = null;
                        me.displayTplData = undefined;
                    }
                    // If the value is blank we can't have a value
                    /*if (me.clearValueOnEmpty) {
                        me.valueCollection.removeAll();
                    }
                    */
                    // Just erased back to empty. Hide the dropdown.
                    me.collapse();

                    // There may have been a local filter if we were querying locally.
                    // Clear the query filter and suppress the consequences (we do not want a list refresh).
                    if (me.queryFilter) {
                        // Must set changingFilters flag for this.checkValueOnChange.
                        // the suppressEvents flag does not affect the filterchange event
                        me.changingFilters = true;
                        me.store.removeFilter(me.queryFilter, true);
                        me.changingFilters = false;
                    }
                }
                me.callParent([e]);
            }
        }
    },

    /**
     * @private
     * Execute the query with the raw contents within the textfield.
     */
    doRawQuery: function() {
        var me = this,
            rawValue = me.inputEl.dom.value;

        // Use final bit after comma as query value if multiSelecting
        if (me.multiSelect) {
            rawValue = rawValue.split(me.delimiter).pop();
        }

        me.doQuery(rawValue, false, true);
    },

    /**
     * Executes a query to filter the dropdown list. Fires the {@link #beforequery} event prior to performing the query
     * allowing the query action to be canceled if needed.
     *
     * @param {String} queryString The string to use to filter available items by matching against the configured {@link #valueField}.
     * @param {Boolean} [forceAll=false] `true` to force the query to execute even if there are currently fewer characters in
     * the field than the minimum specified by the `{@link #minChars}` config option. It also clears any filter
     * previously saved in the current store.
     * @param {Boolean} [rawQuery=false] Pass as true if the raw typed value is being used as the query string. This causes the
     * resulting store load to leave the raw value undisturbed.
     * @return {Boolean} true if the query was permitted to run, false if it was cancelled by a {@link #beforequery}
     * handler.
     */
    doQuery: function(queryString, forceAll, rawQuery) {
        var me = this,

        // Decide if, and how we are going to query the store
            queryPlan = me.beforeQuery({
                query: queryString || '',
                rawQuery: rawQuery,
                forceAll: forceAll,
                combo: me,
                cancel: false
            });

        // Allow veto.
        if (queryPlan !== false && !queryPlan.cancel) {

            // If they're using the same value as last time (and not being asked to query all), just show the dropdown
            if (me.queryCaching && queryPlan.query === me.lastQuery) {
                me.expand();
            }

            // Otherwise filter or load the store
            else {
                me.lastQuery = queryPlan.query;

                if (me.queryMode === 'local') {
                    me.doLocalQuery(queryPlan);

                } else {
                    me.doRemoteQuery(queryPlan);
                }
            }
        }

        return true;
    },
    /**
     * @template
     * A method which may modify aspects of how the store is to be filtered (if {@link #queryMode} is `"local"`)
     * of loaded (if {@link #queryMode} is `"remote"`).
     *
     * This is called by the {@link #doQuery method, and may be overridden in subclasses to modify
     * the default behaviour.
     *
     * This method is passed an object containing information about the upcoming query operation which it may modify
     * before returning.
     *
     * @param {Object} queryPlan An object containing details about the query to be executed.
     * @param {String} queryPlan.query The query value to be used to match against the ComboBox's {@link #valueField}.
     * @param {Boolean} queryPlan.forceAll If `true`, causes the query to be executed even if the minChars threshold is not met.
     * @param {Boolean} queryPlan.cancel A boolean value which, if set to `true` upon return, causes the query not to be executed.
     * @param {Boolean} queryPlan.rawQuery If `true` indicates that the raw input field value is being used, and upon store load,
     * the input field value should **not** be overwritten.
     *
     */
    beforeQuery: function(queryPlan) {
        var me = this;

        // Allow beforequery event to veto by returning false
        if (me.fireEvent('beforequery', queryPlan) === false) {
            queryPlan.cancel = true;
        }

        // Allow beforequery event to veto by returning setting the cancel flag
        else if (!queryPlan.cancel) {

            // If the minChars threshold has not been met, and we're not forcing an "all" query, cancel the query
            if (queryPlan.query.length < me.minChars && !queryPlan.forceAll) {
                queryPlan.cancel = true;
            }
        }
        return queryPlan;
    },

    doLocalQuery: function(queryPlan) {
        var me = this,
            queryString = queryPlan.query,
            store = me.getStore(),
            filter = me.queryFilter,
            matches = 0;

        me.queryFilter = null;

        // Must set changingFilters flag for this.checkValueOnChange.
        // the suppressEvents flag does not affect the filterchange event
        me.changingFilters = true;
        if (filter) {
            store.removeFilter(filter, true);
        }

        // Querying by a string...
        if (queryString) {
            filter = me.queryFilter = new Ext.util.Filter({
                filterFn: function(node) {
                    var children = node.childNodes,
                        len = children && children.length,

                        anyMatch= me.anyMatch,
                        caseSensitive= me.caseSensitive,
                        value = me.enableRegEx ? new RegExp(queryString) : queryString,

                    // Visibility of leaf nodes is whether they pass the test.
                    // Visibility of branch nodes depends on them having visible children.
                        visible = node.isLeaf() ? value.test(node.get(me.displayField)) : false,
                        i;

                    // We're visible if one of our child nodes is visible.
                    // No loop body here. We are looping only while the visible flag remains false.
                    // Child nodes are filtered before parents, so we can check them here.
                    // As soon as we find a visible child, this branch node must be visible.
                    for (i = 0; i < len && !(visible = children[i].get('visible')); i++);

                    if (visible && node.isLeaf()) {
                        matches++;
                    }
                    return visible;
                },
                id: me.id + '-filter'

            });
            store.addFilter(filter, true);
        }
        me.changingFilters = false;

        // Expand after adjusting the filter if there are records or if emptyText is configured.
        if (me.store.getCount() || me.getPicker().emptyText) {
            // The filter changing was done with events suppressed, so
            // refresh the picker DOM while hidden and it will layout on show.
            me.getPicker().getView().refresh();
            me.expand();
        } else {
            me.collapse();
        }

        me.afterQuery(queryPlan);
    },

    doRemoteQuery: function(queryPlan) {
        var me = this,
            loadCallback = function() {
                if (!me.destroyed) {
                    me.afterQuery(queryPlan);
                }
            };

        // expand before loading so LoadMask can position itself correctly
        me.expand();

        // In queryMode: 'remote', we assume Store filters are added by the developer as remote filters,
        // and these are automatically passed as params with every load call, so we do *not* call clearFilter.

        me.store.load({
            params: me.getParams(queryPlan.query),
            rawQuery: queryPlan.rawQuery,
            callback: loadCallback
        });

    },

    /**
     * @template
     * A method called when the filtering caused by the {@link #doQuery} call is complete and the store has been
     * either filtered locally (if {@link #queryMode} is `"local"`), or has been loaded using the specified filtering.
     *
     * @param {Object} queryPlan An object containing details about the query was executed.
     * @param {String} queryPlan.query The query value to be used to match against the ComboBox's {@link #valueField}.
     * @param {Boolean} queryPlan.forceAll If `true`, causes the query to be executed even if the minChars threshold is not met.
     * @param {Boolean} queryPlan.cancel A boolean value which, if set to `true` upon return, causes the query not to be executed.
     * @param {Boolean} queryPlan.rawQuery If `true` indicates that the raw input field value is being used, and upon store load,
     * the input field value should **not** be overwritten.
     */
    afterQuery: function(queryPlan) {
        var me = this;

        if (me.store.getCount()) {
            if (me.typeAhead) {
                me.doTypeAhead();
            }

            if (queryPlan.rawQuery) {
                if (me.picker && !me.picker.getSelectionModel().hasSelection()) {
                    me.doAutoSelect();
                }
            } else {
                me.doAutoSelect();
            }
        }

        // doQuery is called upon field mutation, so check for change after the query has done its thing
        me.checkChange();
    },


    /**
     * @private
     * If the autoSelect config is true, and the picker is open, highlights the first item.
     */
    doAutoSelect: function() {
        var me = this,
            picker = me.picker;

        if (picker && me.autoSelect && me.store.getCount() > 0) {
            // Highlight the last selected item and scroll it into view
            picker.getNavigationModel().setPosition(me.picker.getSelectionModel().lastSelected || 0);
        }
    },

    doTypeAhead: function() {
        var me = this,
            Event = Ext.event.Event;
        if (!me.typeAheadTask) {
            me.typeAheadTask = new Ext.util.DelayedTask(me.onTypeAhead, me);
        }
        if (me.lastKey !== Event.BACKSPACE && me.lastKey !== Event.DELETE) {
            me.typeAheadTask.delay(me.typeAheadDelay);
        }
    },

    onTypeAhead: function() {
        var me = this,
            displayField = me.displayField,
            record = me.store.findRecord(displayField, me.getRawValue()),
            tree = me.getPicker(),
            newValue, len, selStart;

        if (record) {
            newValue = record.get(displayField);
            len = newValue.length;
            selStart = me.getRawValue().length;

           // tree.highlightItem(boundList.getNode(record));

            if (selStart !== 0 && selStart !== len) {
                me.setRawValue(newValue);
                me.selectText(selStart, newValue.length);
            }
        }
    },

    onTriggerClick: function() {
        var me = this;

        if (!me.readOnly && !me.disabled) {
            if (me.isExpanded) {
                me.collapse();
            } else {
                if (me.triggerAction === 'all') {
                    me.doQuery(me.allQuery, true);
                } else if (me.triggerAction === 'last') {
                    me.doQuery(me.lastQuery, true);
                } else {
                    me.doQuery(me.getRawValue(), false, true);
                }
            }
        }
    },

});

//@ sourceURL=/ux/TreeCombo.js