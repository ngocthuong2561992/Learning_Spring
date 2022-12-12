Ext.define('ext.View', {
	extend: 'Ext.form.Panel',
	header: false,
	width : '100%',
	border: false,
	layout: 'border',
	initComponent : function() {

		let me = this;

		// Ext.tip.Tip.prototype.minWidth = void 0;
		// Ext.tip.QuickTipManager.init();

		let targetStore = Ext.widget('mestore');
		let yearStore = Ext.widget('mestore');
		let versionStore = Ext.widget('mestore');
		let typeStore = Ext.widget('metreestore');
		let statusStore = Ext.widget('mestore');
		let levelStore = Ext.widget('metreestore');
		let gradeStore = Ext.widget('mestore');

		let targetInfoStore = Ext.widget('mestore');
		let yearInfoStore = Ext.widget('mestore');
		let typeInfoStore = Ext.widget('metreestore');
		let statusInfoStore = Ext.widget('mestore');
		let versionInfoStore = Ext.widget('mestore');
		let yleStore = Ext.widget('mestore');

		let btnSearch = Ext.widget('mebutton', {
			text: 'Search',
			padding: '8 10',
			iconCls: 'fa fa-search btn-icon',
			style: 'float : right;',
			handler: function() {
				products();
				// getSignature();
			}
		});

		let formSearch = Ext.create('Ext.form.Panel', {
			width : '100%',
			border : false,
			padding : '10 0 0 10',
			style : 'border:1px solid #b5b8c8; border-radius:3px;',
			layout: {
				type: 'table',
				columns: 3,
				tdAttrs: {style: 'padding: 0px 10px 0px 0px; vertical-align : top;'}
			},
			items: [
				{xtype: 'mecombo', width: '100%', labelWidth: 70, name: 'target', fieldLabel: 'Test Target',
					store: targetStore, valueField: 'detailCode', displayField: 'codeName', queryMode: 'local', editable: false,
					matchFieldWidth: false,
					listConfig: {
						listeners: {
							beforeshow: function(picker) {
//								picker.minWidth = 300;
							}
						}
					}
				},
				{xtype: 'mecombo', width: '100%', labelWidth: 60, name: 'year', fieldLabel: 'Year',
					store: yearStore, valueField: 'detailCode', displayField: 'codeName', queryMode: 'local', editable: false,
				},
				{xtype: 'mecombo', width: '100%', labelWidth: 45, name: 'version', fieldLabel: 'Version',
					store: versionStore, valueField: 'detailCode', displayField: 'codeName', queryMode: 'local', editable: false,
				},
				{xtype: 'treecombomulti', store: levelStore, labelWidth : 70, fieldLabel : 'Level', width: '100%', treeWidth: 200, editable: false, name: 'level'},
				{xtype: 'treecombotesttype', store: typeStore, labelWidth : 60, fieldLabel: 'Test Type', width: '100%',
					treeWidth: 200, editable: false, name: 'type'
				},
				{xtype: 'mecombo', width: '100%', labelWidth: 45, name: 'status', fieldLabel: 'Status',
					store: statusStore, valueField: 'detailCode', displayField: 'codeName', queryMode: 'local', editable: false,
				},
				{xtype: 'metext', width: '100%', colspan: 2, labelWidth: 70, name: 'name', fieldLabel: 'Test Name',
					listeners: {
						specialkey: function (f, e) {
							if (e.getKey() == e.ENTER) {
							}
						}
					}
				},
				btnSearch,
			]
		});
//
		let mainStore = Ext.widget('mestore', {
			pageSize: 20,
			proxy: {
				type : 'ajax',
				url: CONTEXT_PATH + '/basicInfomationMngt/getTestFrameworkList.json',
				reader: {
					type: 'json',
					enablePaging: true,
					rootProperty: 'data',
					totalProperty: 'totalCount',
				}
			},
			queryMode: 'local',
		});
		let mainGrid = Ext.create('Ext.grid.Panel', {
			width: '100%',
			flex: 1,
			store: mainStore,
			margin: '5 0 0 0',
			selModel: Ext.create('Ext.selection.CheckboxModel'),
			columns: [
				{text : 'No', width: 40, dataIndex: 'id', align: 'center', sortable: false, menuDisabled: true},
				{text : 'Test Name', minWidth: 150, flex: 1, dataIndex: 'description', align : 'center', sortable: false, menuDisabled: true},
				{text : 'Test Type', minWidth: 150, flex: 1, dataIndex: 'testTypeName', align : 'center', sortable: false, menuDisabled: true},
				{text : 'From Date', minWidth: 100, flex: 1, dataIndex: 'startDate', align : 'center', sortable: false, menuDisabled: true,
					renderer: function(value, metaData, record, row, col, store, gridView) {
						return stringToDate(value);
					}
				},
				{text : 'Status', minWidth: 100, flex: 1, dataIndex: 'useYnName', align : 'center', sortable: false, menuDisabled: true},
				{text : 'Year', minWidth: 100, flex: 1, dataIndex: 'yearCode', align : 'center', sortable: false, menuDisabled: true},
				{text : 'Version', minWidth: 100, flex: 1, dataIndex: 'versionName', align : 'center', sortable: false, menuDisabled: true},
				{text : 'Target', minWidth: 100, flex: 1, dataIndex: 'targetName', align : 'center', sortable: false, menuDisabled: true},
			],
			listeners: {
				cellclick: function (view, cell, cellIndex, record, row, rowIndex, e) {
					if(cellIndex != 0) {
						rightPanel.setDisabled(false);
					}
				},
			},
			bbar : new Ext.PagingToolbar({
				store : mainStore,
				pageSize : 20,
				displayInfo : true,
				listeners : {
					beforechange : function(pagingtoolbar, page, eOpts) {
						rightPanel.setDisabled(true);
					}
				}
			}),
		});

		let btnExcel = Ext.widget('mebutton', {
			text: 'Excel',
			iconCls: 'far fa-file-excel btn-icon',
			padding: '8 10',
			margin: '5 0 0 0',
			menu: [
				{text: 'Selected output',
					handler: function(value) {
					}
				},
				{text: 'Current Page',
					handler: function(value) {
					}
				},
				{text: 'All output',
					handler: function(value) {
					}
				}
			]
		});
		let btnRegister = Ext.widget('mebutton', {
			text: 'New Registration',
			iconCls: 'far fa-file btn-icon',
			padding: '8 10',
			margin: '5 0 0 0',
			handler: function() {
				exportPdf();
			}
		});

		let btnCopyTestFW = Ext.widget('mebutton', {
			text: 'Copy Test Framework Information',
			iconCls: 'far fa-copy btn-icon',
			padding: '8 10',
			hidden: !me.writeAuth,
			hideMode: 'visibility',
			handler: function() {
			}
		});
		let formInfo = Ext.create('Ext.form.Panel', {
			width: '100%',
			border: false,
			layout: 'column',
			items: [
				{xtype: 'mecombo', columnWidth: .25, fieldLabel: 'Test Target', labelWidth: 70, name: 'target', allowBlank: false,
					store: targetInfoStore, valueField: 'detailCode', displayField: 'codeName', queryMode: 'local', editable: false,
				},
				{xtype: 'mecombo', columnWidth: .25, fieldLabel: 'Test Status', labelWidth: 70, name: 'status', margin: '0 0 0 10', allowBlank: false,
					store: statusInfoStore, valueField: 'detailCode', displayField: 'codeName', queryMode: 'local', editable: false,
				},
				{xtype: 'mecombo', columnWidth: .25, fieldLabel: 'Year', labelWidth: 40, name: 'year', margin: '0 0 0 10', allowBlank: false, style: 'float: right;',
					store: yearInfoStore, valueField: 'detailCode', displayField: 'codeName', queryMode: 'local', editable: false,
				},
				{xtype: 'mecombo', columnWidth: .25, fieldLabel: 'Version', labelWidth: 55, name: 'version', margin: '0 0 0 10', allowBlank: false,
					store: versionInfoStore, valueField: 'detailCode', displayField: 'codeName', queryMode: 'local', editable: false,
				},
				{xtype: 'treecombotesttype', columnWidth: .25, fieldLabel: 'Test Type', labelWidth: 70, name: 'type', margin: '10 0 0 0', allowBlank: false,
					store: typeInfoStore, editable: false, rootVisibleCustom: false,
					select: function(value) {
						let testType = typeInfoStore.findNode('id', value).data;
						getFormField(formInfo, 'yleLevel').setDisabled(!isYLE(testType.treeId));
					}
				},
				{xtype: 'mecombo', columnWidth: .25, fieldLabel: 'YLE Level', labelWidth: 70, name: 'yleLevel', margin: '10 0 0 10', allowBlank: false,
					store: yleStore, valueField: 'detailCode', displayField: 'codeName', queryMode: 'local', editable: false,
				},
				{xtype: 'fieldcontainer', columnWidth: .5, layout: 'hbox', labelWidth: 55, margin: '10 0 0 10', labelSeparator: '',
					fieldLabel: 'Period' + '<span style = "color:red;">*</span>',
					items: [
						{xtype: 'medate', flex: .45, name: 'start', value: new Date(new Date().getFullYear(), new Date().getMonth(), 1), allowBlank: false,
							listeners: {
								change: function(combo, value){
									if(this.value != null){
										getFormField(formInfo, 'end').setMinValue(this.value);
									}
								}
							}
						},
						{xtype: 'medisplayfield', flex: .1, value: '~', style: 'text-align: center;'},
						{xtype: 'medate', flex: .45, name: 'end', value: new Date(), style: 'float: right;', allowBlank: false}
					]
				},
				{xtype: 'fieldcontainer', columnWidth: 1, layout: 'hbox', fieldLabel: ' ', labelWidth: 70, labelSeparator: '',
					items: [
						btnCopyTestFW
					]
				},
				{xtype: 'medisplayfield', columnWidth: .5, fieldLabel: 'Test Code', labelWidth: 70, name: 'code', margin: '10 0 0 0', readOnly: true},
				{xtype: 'mecombo', columnWidth: .5, fieldLabel: 'Grade', labelWidth: 55, name: 'grade', margin: '10 0 0 10', multiSelect: true,
					store: gradeStore, valueField: 'detailCode', displayField: 'codeName', queryMode: 'local', editable: false,
					triggers : {
						clear : {
							cls : 'x-form-clear-trigger', weight: 1,
							handler : function() {
								this.setValue();
							}
						}
					},
					listConfig: {
						tpl: Ext.create('Ext.XTemplate',
							'<li class="comboboxMulti">',
							'<tpl for=".">',
							'<div class="boundList x-boundlist-item" style="background:none;border:none;">',
							'<span class="checkbox" style="display: inline-block; margin-right: 5px;" value={detailCode}></span>{codeName}',
							'</div>',
							'</tpl>',
							'</li>'
						)
					},
					listeners: {
						afterrender: function() {
							let tree = this;
							let tooltip = {
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
					}
				},
				{xtype: 'metext', columnWidth: 1, fieldLabel: 'Test Name', labelWidth: 70, name: 'name', margin: '10 0 0 0', allowBlank: false},
				{xtype: 'metext', columnWidth: 1, labelWidth: 70, name: 'applyStats', fieldLabel: 'Apply Stats', editable: false, margin: '10 0 0 0',
					triggers: {
						clear: {
							cls: 'x-form-clear-trigger', hidden: true,
							handler: function() {
								this.reset();
								getFormField(formInfo, 'applyStats').reset();
								getFormField(formInfo, 'applyStats').applyStatsCodeList = '';
								this.getTriggers().clear.hide();
							}
						},
						search: {
							cls: 'x-form-search-trigger',
							handler: function() {
								if(!me.writeAuth) return;
							}
						}
					},
					listeners: {
						change: function() {
							if(this.value != '') {
								this.getTriggers().clear.show();
							}else {
								this.getTriggers().clear.hide();
							}
						}
					}
				},
				{xtype: 'mearea', columnWidth: 1, fieldLabel: 'Note', labelWidth: 70, name: 'note', margin: '10 0 0 0'}
			]
		});

		let leftPanel = Ext.widget('panel', {
			width: '50%',
			layout: 'vbox',
			border: false,
			region: 'center',
			split: true,
			bodyPadding: 5,
			items: [
				formSearch,
				mainGrid,
				{xtype: 'container', width : '100%', layout : 'hbox',
					items: [btnExcel, {xtype: 'tbfill'}, btnRegister]
				}
			]
		});

		let rightPanel = Ext.widget('panel', {
			region : 'east',
			collapsible : true,
			border : false,
			header : false,
			layout : 'vbox',
			width : '50%',
			split : true,
			bodyPadding: 5,
			autoScroll: true,
			items: [
				formInfo,
				{xtype: 'filefield', fieldLabel: 'Photo', buttonText: 'Select Photo...',
					listeners: {
						change: function(field, fileName) {
							var fileList = field.fileInputEl.dom.files;
							var file = fileList[0];
							var reader = new FileReader();
							reader.onload = function(event){
								let data = reader.result;
								let arr = data.split(/[\n]/);
								console.log(arr);
								arr = _(arr)
									.map(s => {
										if(s.includes('-->')) {
											s = s.replaceAll('\r','');
											let timeArr = s.split(' --> ');
											let start = calculTime(timeArr[0]);
											let end = calculTime(timeArr[1]);
											return start + ' --> ' + end + '\r';
										}
										return s;
									})
									.values();
								// arr.forEach(s => {
								// 	if(s.includes('-->')) {
								// 		s = s.replaceAll('\r','');
								// 		let timeArr = s.split(' --> ');
								// 		let start = calculTime(timeArr[0]);
								// 		let end = calculTime(timeArr[1]);
								// 		s = start + ' --> ' + end + '\r';
								// 	}
								// });
								console.log(arr.join(''))
							};
							reader.onerror = function(){
								console.log('On Error Event');
							};
							reader.readAsText(file);
						}
					}
				},
			],
		});
		function calculTime(input) {
			var arr = input.split(':');
			var h = parseFloat(arr[0]),
				m = parseFloat(arr[1]),
				s = parseFloat(arr[2].replaceAll(',','.'));
			s = s - 0.5;
			// 00:58:00.316
			if(s < 0) {
				s = s + 60;
				m = m - 1;
				if(m < 0) {
					m = m + 60;
					h = h - 1;
				}
			}else if(s > 60) {
				s = s - 60;
				m = m + 1;
				if(m > 60) {
					m = m - 60;
					h = h + 1;
				}
			}
			s = formatNumber(s, 3);
			if(h < 10) h = '0' + h;
			if(m < 10) m = '0' + m;
			if(s < 10) s = '0' + s;
			return h + ':' + m + ':' + s
		}
		// console.log(calculTime('00:58:00.316'));

		this.items = [leftPanel, rightPanel];
		this.callParent(arguments);

		async function products() {
			try {
				let params = {
					"currentPage": 0,
					"pageSize": 2
				};
				let ajaxUrl = 'api/products';
				let json = await getDataAjax(ajaxUrl, params);
				console.log(json);
				mainStore.loadData(json.content);
			}catch(e) {
				handleException(e);
			}
		}

		function exportPdf() {
			var form = Ext.create('Ext.form.Panel', {
				standardSubmit: true,
				url: CONTEXT_PATH + 'api/exportPdf',
				method: 'GET'
			});
			form.submit({
				target: '_blank',
			});
		}

		function getSignature() {
			let body = {
				"amount":37400000,
				"payment_reference_id":"NEW202108250000",
			};
			body = JSON.stringify(body);
			console.log(body)
//			body = '{"request_id":"e7271533-898d-46a9-bef6-e12b12f4bba3","amount":5000000,"currency":"VND","merchant_ext_id":"Pru_012345","store_ext_id":"Pru_01","payment_reference_id":"NEW202108250001"}';
// 			body = body.replaceAll('"', '\\"');
			let hash = CryptoJS.HmacSHA256(body, '1912010b01904df08e47dc6e2907df2f')
			let sig = CryptoJS.enc.Base64.stringify(hash).replace(/\n+$/, '')
			console.log(sig)
		}

//		END
	}
});

