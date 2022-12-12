Ext.define('Common', {
	statics : {
		stringTodate : function(dateString){
			dateString = Ext.String.trim(dateString);
			var date;
			if(dateString.length == 8){
				var year = dateString.substr(0,4);
				var month = dateString.substr(4,2)-1;
				var day = dateString.substr(6,2);
				date = new Date(year, month, day)
				
			}
			return date;
		},
		dateToString : function(date){
			var resultDate = new Date(date);
			  
	        year = resultDate.getFullYear();
	        month = resultDate.getMonth() + 1;
	        day = resultDate.getDate();
	        if(month < 10)
	        	month = "0"+month;
	        if(day < 10)
	        	day = "0"+day;
	        return year + "" + month + "" + day;
		},
		isString: function(value) {
			return typeof value === 'string' || value instanceof String;
		},
		isNumber: function(value) {
			return typeof value === 'number' && isFinite(value);
		},
		isArray: function(value) {
			return value && typeof value === 'object' && value.constructor === Array;
		},
		isObject: function(value) {
			return value && typeof value === 'object' && value.constructor === Object;
		},
		isFunction: function(value) {
			return typeof value === 'function';
		},
		isBoolean: function(value) {
			return typeof value === 'boolean';
		},
		isRegExp: function(value) {
			return value && typeof value === 'object' && value.constructor === RegExp;
		},
		isError: function(value) {
			return value instanceof Error && typeof value.message !== 'undefined';
		},
		isDate: function(value) {
			return value instanceof Date;
		},
		isSymbol: function(value) {
			return typeof value === 'symbol';
		},
		formatNumber: function(value, decimalPlaces) {
			return Number(Math.round(value + 'e' + decimalPlaces) + 'e-' + decimalPlaces)
		},
		getGeneralCode: function(stores, systemCode, commonCode, opt, items) {
			let deferred = new Ext.Deferred();
			Ext.Ajax.request({
				url : URL_GENERAL_CODE,
				params : {
					systemCode 	: systemCode, 
					commonCode 	: commonCode,
					useYn 		: 'Y'
				},
				success : function(response) {
					if(checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut()
						return;
					}
					var json = Ext.decode(response.responseText);
					var commonCodeArray = json.data;
					
					stores.loadData(commonCodeArray);
					
					if(opt.isAddNewOption == true){
						var commonCode = {
							codeName 	: opt.newDisplayField,
							detailCode  : opt.newValueField
						};
						stores.insert(0, commonCode);
					}
					
					if(opt.isSelectedValue == true){
						items.setValue(opt.selectedValue);
					}else if(opt.isSelectFirst == true) {
						items.setValue(stores.first());
					}
					deferred.resolve();
				},
				error : function(response) {
					if( checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut();
					}
					deferred.reject({message: 'error'});
				},
				failure: function(response){
					if( checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut();
					}
					deferred.reject({message: 'failure'});
				}
			});
			return deferred.promise;
		},
		getGeneralCodeKEMS: function(stores, systemCode, commonCode, opt, items) {
			let deferred = new Ext.Deferred();
			Ext.Ajax.request({
				url : CONTEXT_PATH + '/general/generalCodeKEMS.json',
				params : {
					systemCode 	: systemCode, 
					commonCode 	: commonCode,
					useYn 		: 'Y'
				},
				success : function(response) {
					if(checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut()
						return;
					}
					var json = Ext.decode(response.responseText);
					var commonCodeArray = json.data;
					
					stores.loadData(commonCodeArray);
					
					if(opt.isAddNewOption == true){
						var commonCode = {
							codeName 	: opt.newDisplayField,
							detailCode  : opt.newValueField
						};
						stores.insert(0, commonCode);
					}
					
					if(opt.isSelectedValue == true){
						items.setValue(opt.selectedValue);
					}else if(opt.isSelectFirst == true) {
						items.setValue(stores.first());
					}
					deferred.resolve();
				},
				error : function(response) {
					if( checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut();
					}
					deferred.reject({message: 'error'});
				},
				failure: function(response){
					if( checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut();
					}
					deferred.reject({message: 'failure'});
				}
			});
			return deferred.promise;
		},
		getTreeCode : function(stores, systemCode, commonCode, treeLevel, parentDetailCode, opt, items){
			let deferred = new Ext.Deferred();
			Ext.Ajax.request({
				url : URL_TREE_CODE,
				params : {
					systemCode 		: systemCode, 
					commonCode 		: commonCode,
					parentDetailCode	: parentDetailCode,
					treeLevel		: treeLevel,
					useYn 			: 'Y'
				},
				success : function(response) {
					if(checkAjaxSessionTimeOut(response)) {
						redirectedWhenSessionTimeOut()
						return;
					}
					var json = Ext.decode(response.responseText);
					stores.loadData(json.data);
					
					if(opt.isAddNewOption == true) {
						var commonCode = {
							codeName 	: opt.newDisplayField,
							detailCode  : opt.newValueField
						};
						stores.insert(0, commonCode);
					}
					if(opt.isSelectedValue == true) {
						items.setValue(opt.selectedValue);
					}else if(opt.isSelectFirst == true) {
						items.setValue(stores.first());
					}
					deferred.resolve();
				},
				error : function(response) {
					if( checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut();
					}
					deferred.reject({message: 'error'});
				},
				failure: function(response){
					if( checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut();
					}
					deferred.reject({message: 'failure'});
				}
			});	
			return deferred.promise;
		},
		getYearList: function(stores, opt, items) {
			var start = 2018;
			var end = new Date().getFullYear() + 1;
			var data = [];
			for(var i = start; i <= end; i++) {
				data.push({
					detailCode  : i,
					codeName 	: i
				});
			}
			
			stores.loadData(data);
			if(opt.isAddNewOption == true) {
				var commonCode = {
					detailCode  : opt.newValueField,
					codeName 	: opt.newDisplayField
				};
				stores.insert(0, commonCode);
			}
			if(opt.isSelectedValue == true) {
				items.setValue(opt.selectedValue);
			}else if(opt.isSelectFirst == true) {
				items.setValue(stores.first());
			}
		},
		
		resetStoreTreeLevel : function(store, params){
			var paramLoad = {
				clienCodeString : '',
				testFwCode : 0,
			}
			if (params.testFwCode != undefined){
				paramLoad.testFwCode = params.testFwCode;
			}
			paramLoad.clienCodeString = params.clienCodeString;
			
			Ext.Ajax.request({
			   	url: CONTEXT_PATH + "/general/getCampusLevelTreeList.json",
			   	params : {clienCodeString : paramLoad.clienCodeString,
			   			  testFwCode      : paramLoad.testFwCode 
			   			},
			   	success : function(response) {
			   		if(checkAjaxSessionTimeOut(response)) {
			   			redirectedWhenSessionTimeOut()
			   			return;
			   		}
					var json = Ext.decode(response.responseText);
					var courseList = json.data;
					
					var checked = undefined ;
					var treePanelLevel= Ext.create("Ext.data.TreeStore", {
		                id: '0',
		                text: 'Course List',
		                checked: false,
		                expanded: true,
		                leaf: true
		            });
			   		if(courseList != null && courseList.length > 0){
			   			store.setRoot(treePanelLevel);
			   			var root = store.getRoot();
						for(var i=0; i < courseList.length; i++){
							var course = courseList[i];
							var parentNode = null;
							if(course.treeLevel =='1'){
								parentNode =  root;
								if(parentNode != null){
									parentNode.appendChild({
						                id: course.courseCode,
						                text: course.courseCodeName,
						                checked : checked,
						                expanded: false
						            }); //add childs
								}
								
							}else{
								parentNode = root.findChild("id", course.parentCrsCode, true);
								if(parentNode != null){
									parentNode.appendChild({
						                id: course.courseCode,
						                text: course.courseCodeName,
						                checked : checked,
						                expanded: false,
						                leaf:true,
						            }); 
								}
								
							}
						}
			   		}else {
						store.setRootNode(null);
					}
				}
					});
		},
		
		getLevelStore: function(store, tree, opt, params){
			Ext.Ajax.request({
				url : CONTEXT_PATH + "/general/getCampusLevelTreeList.json",
				params : {
					clienCodeString : params.clienCodeString == undefined ? '' : params.clienCodeString,
	   			  	testFwCode      : params.testFwCode == undefined ? 0 : params.testFwCode
				},
				success : function(response) {
					if(checkAjaxSessionTimeOut(response)) {
						redirectedWhenSessionTimeOut()
						return;
					}
					var json = Ext.decode(response.responseText);
					var courseList = json.data;
					var root = {
						text: 'Root',
						id: 'root',
						expanded: true,
						checked: false,
						children: []
					};
					var count = -1;
					Ext.each(courseList, function(item) {
						if (item.treeLevel == 1) {
							root.children.push({
								id: item.courseCode, 
								text: item.courseCodeName, 
								leaf: false, 
								checked: false, 
								children: []
							});
							count++;
							Ext.each(courseList, function(record) {
								if (record.treeLevel == 2 && record.parentCrsCode == item.courseCode) {
									root.children[count].children.push({
										id: record.courseCode, 
										text: record.courseCodeName, 
										checked: false, 
										leaf: true
									})
								}
							});
						}
					})
					store.setRoot(root);
					if(opt.isAddNewOption == true) {
						tree.setDefaultText(opt.newDisplayField);
					}
					if(opt.isSelectedValue == true) {
						tree.setValue(opt.selectedValue)
					}
					tree.okFunction();
				},
			});
		},
		
		getLevelStoreStatistic: function(store, tree, opt, params){
			Ext.Ajax.request({
				url : CONTEXT_PATH + "/general/getCampusLevelTreeListStatistic.json",
				params : {
					clienCodeString : params.clienCodeString == undefined ? '' : params.clienCodeString,
	   			  	testFwCode      : params.testFwCode == undefined ? 0 : params.testFwCode
				},
				success : function(response) {
					if(checkAjaxSessionTimeOut(response)) {
						redirectedWhenSessionTimeOut()
						return;
					}
					var json = Ext.decode(response.responseText);
					var courseList = json.data;
					var root = {
						text: 'Root',
						id: 'root',
						expanded: true,
						children: []
					};
					var count = -1;
					Ext.each(courseList, function(item) {
						if (item.treeLevel == 1) {
							root.children.push({
								id: item.courseCode, 
								text: item.courseCodeName, 
								leaf: false, 
								children: []
							});
							count++;
							Ext.each(courseList, function(record) {
								if (record.treeLevel == 2 && record.parentCrsCode == item.courseCode) {
									root.children[count].children.push({
										id: record.courseCode, 
										text: record.courseCodeName, 
										leaf: true
									})
								}
							});
						}
					})
					store.setRoot(root);
					if(opt.isAddNewOption == true) {
						tree.setDefaultText(opt.newDisplayField);
					}
					if(opt.isSelectedValue == true) {
						tree.setValue(opt.selectedValue)
					}
				},
			});
		},
		
		getTestTypeStore: function(store, tree, opt) {
			let deferred = new Ext.Deferred();
			Ext.Ajax.request({
				url : CONTEXT_PATH + "/general/getTestTypeListForTree.json",
				params : {},
				success : function(response) {
					if(checkAjaxSessionTimeOut(response)) {
						redirectedWhenSessionTimeOut()
						return;
					}
					var json = Ext.decode(response.responseText);
					var testTypeList = json.data;
					let selectedTestTypeValue = 0;
					var root = {
						text: 'All',
						id: '0',
						treeId: '',
						expanded: true,
						children: []
					};
					var count = -1;
					Ext.each(testTypeList, function(item) {
						if (item.treeLevel == '1') {
							root.children.push({
								id: item.detailCode, 
								text: item.codeName, 
								leaf: false, 
								children: []
							});
							count++;
							Ext.each(testTypeList, function(record) {
								if(  record.treeLevel == '2' 
										&& opt.selectedTestTypeTreeID != undefined 
										&&  opt.selectedTestTypeTreeID != null    
										&&  opt.selectedTestTypeTreeID  == record.treeId
										
								){
									selectedTestTypeValue = record.detailCode;
								}
								if (record.treeLevel == '2' && record.parentDetailCode == item.detailCode) {
									root.children[count].children.push({
										id: record.detailCode, 
										text: record.codeName, 
										treeId: record.treeId,
										leaf: true
									})
								}
							});
						}
					})
					store.setRoot(root);
					tree.data = json.data;
					if(opt.isAddNewOption == true) {
						tree.setDefaultText(opt.newDisplayField);
					}
					if(opt.isSelectedValue == true) {
						tree.setValue(opt.selectedValue)
					}
					
					if(   opt.isSelectedTestType != undefined 
							  &&  opt.isSelectedTestType != null    
								&& selectedTestTypeValue >0
								
						){
							tree.setValue(selectedTestTypeValue );
						}
						
					deferred.resolve();
				},
				error : function(response) {
					if( checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut();
					}
					deferred.reject({message: 'error'});
				},
				failure: function(response){
					if( checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut();
					}
					deferred.reject({message: 'failure'});
				}
			});
			return deferred.promise;
		},
		
		getPlatTestTypeStore: function(store, tree, opt) {
			let deferred = new Ext.Deferred();
			Ext.Ajax.request({
				url : CONTEXT_PATH + "/general/getTestTypeListForTree.json",
				params : {},
				success : function(response) {
					if(checkAjaxSessionTimeOut(response)) {
						redirectedWhenSessionTimeOut()
						return;
					}
					var json = Ext.decode(response.responseText);
					var testTypeList = json.data;
					var root = {
						text: 'All',
						id: '0',
						expanded: true,
						children: []
					};
					var count = -1;
					Ext.each(testTypeList, function(item) {
						if (item.treeLevel == '1') {
							root.children.push({
								id: item.detailCode, 
								text: item.codeName, 
								leaf: false, 
								children: []
							});
							count++;
							Ext.each(testTypeList, function(record) {
								if (record.treeLevel == '2' && record.parentDetailCode == item.detailCode && (record.treeId == 'PT' || record.treeId == 'AT')) {
									root.children[count].children.push({
										id: record.detailCode, 
										text: record.codeName, 
										leaf: true
									})
								}
							});
						}
					})
					store.setRoot(root);
					tree.data = json.data;
					if(opt.isAddNewOption == true) {
						tree.setDefaultText(opt.newDisplayField);
					}
					if(opt.isSelectedValue == true) {
						tree.setValue(opt.selectedValue)
					}
					deferred.resolve();
				},
				error : function(response) {
					if( checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut();
					}
					deferred.reject({message: 'error'});
				},
				failure: function(response){
					if( checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut();
					}
					deferred.reject({message: 'failure'});
				}
			});
			return deferred.promise;
		},
		
		getSubjectStore: function(store, tree, opt, params) {
			var levelCodeList = params == undefined ? '' : params.levelCodeList;
			var getSubjectByLevel = params == undefined ? false : params.getSubjectByLevel;
			Ext.Ajax.request({
				url : CONTEXT_PATH + "/general/getSubjectTreeList.json",
				params : {
					levelCodeList		: levelCodeList,
					getSubjectByLevel	: getSubjectByLevel
				},
				success : function(response) {
					if(checkAjaxSessionTimeOut(response)) {
						redirectedWhenSessionTimeOut()
						return;
					}
					var json = Ext.decode(response.responseText);
					var subjectList = json.data;
					var root = {
						text: 'All',
						id: '0',
						expanded: true,
						children: []
					};
					var count = -1;
					Ext.each(subjectList, function(item) {
						if (item.treeLevel == 1) {
							root.children.push({
								id: item.subjectCode, 
								text: '('+item.subjectCode+') '+item.subjectName, 
								leaf: false, 
								children: []
							});
							count++;
							Ext.each(subjectList, function(record) {
								if (record.treeLevel == 2 && record.parentSubCode == item.subjectCode) {
									root.children[count].children.push({
										id: record.subjectCode, 
										text: '('+record.subjectCode+') '+record.subjectName, 
										leaf: true
									})
								}
							});
						}
					})
					store.setRoot(root);
					if(opt.isAddNewOption == true) {
						tree.setDefaultText(opt.newDisplayField);
					}
					if(opt.isSelectedValue == true) {
						tree.setValue(opt.selectedValue)
					}
				},
			});
		},
		
		getSubjectByCampusStore: function(store, tree, opt, params) {
			var clientCode = params == undefined ? '' : params.clientCode;
			Ext.Ajax.request({
				url : CONTEXT_PATH + "/general/getSubjectTreeListByCampus.json",
				params : {
					clientCode		: clientCode,
				},
				success : function(response) {
					if(checkAjaxSessionTimeOut(response)) {
						redirectedWhenSessionTimeOut()
						return;
					}
					var json = Ext.decode(response.responseText);
					var subjectList = json.data;
					var root = {
						text: 'All',
						id: '0',
						expanded: true,
						children: []
					};
					var count = -1;
					Ext.each(subjectList, function(item) {
						if (item.treeLevel == 1) {
							root.children.push({
								id: item.subjectCode, 
								text: '('+item.subjectCode+') '+item.subjectName, 
								leaf: false, 
								children: []
							});
							count++;
							Ext.each(subjectList, function(record) {
								if (record.treeLevel == 2 && record.parentSubCode == item.subjectCode) {
									root.children[count].children.push({
										id: record.subjectCode, 
										text: '('+record.subjectCode+') '+record.subjectName, 
										leaf: true
									})
								}
							});
						}
					})
					store.setRoot(root);
					if(opt.isAddNewOption == true) {
						tree.setDefaultText(opt.newDisplayField);
					}
					if(opt.isSelectedValue == true) {
						tree.setValue(opt.selectedValue)
					}
				},
			});
		},
		
		getSubjectByCampusStoreStatistic: function(store, tree, opt, params) {
			Ext.Ajax.request({
				url : CONTEXT_PATH + "/general/getSubjectTreeListByMultiCampus.json",
				params : {
					clientCodeList : params.clientCodeList == undefined ? '' : params.clientCodeList,
				},
				success : function(response) {
					if(checkAjaxSessionTimeOut(response)) {
						redirectedWhenSessionTimeOut()
						return;
					}
					var json = Ext.decode(response.responseText);
					var subjectList = json.data;
					var root = {
						text: 'All',
						id: '0',
						expanded: true,
						checked: false,
						children: []
					};
					var count = -1;
					Ext.each(subjectList, function(item) {
						if (item.treeLevel == 1) {
							root.children.push({
								id: item.subjectCode, 
								text: '('+item.subjectCode+') '+item.subjectName, 
								leaf: false, 
								checked: false,
								children: []
							});
							count++;
							Ext.each(subjectList, function(record) {
								if (record.treeLevel == 2 && record.parentSubCode == item.subjectCode) {
									root.children[count].children.push({
										id: record.subjectCode, 
										text: '('+record.subjectCode+') '+record.subjectName, 
										checked: false,
										leaf: true
									})
								}
							});
						}
					})
					store.setRoot(root);
					if(opt.isAddNewOption == true) {
						tree.setDefaultText(opt.newDisplayField);
					}
					if(opt.isSelectedValue == true) {
						tree.setValue(opt.selectedValue)
					}
				},
			});
		},
		
		getStatisticSubjectList: function(callBackFunction) {
			var list = [
	            {subjectCode: 38, subjectName: 'Phonics'},
	            {subjectCode: 28, subjectName: 'Reading'},
	            {subjectCode: 43, subjectName: 'Writing'},
	            {subjectCode: 40, subjectName: 'Listening'},
	            {subjectCode: 42, subjectName: 'Speaking'},
			]
			callBackFunction.call(this, list);
		},
		getCampusList: function(store, field, statistic, callBackFunction) {
			let deferred = new Ext.Deferred();
			Ext.Ajax.request({
				url : CONTEXT_PATH + "/general/getCampusListTestCenter.json",
				success : function(response) {
					if(checkAjaxSessionTimeOut(response)) {
						redirectedWhenSessionTimeOut()
						return;
					}
					var data = Ext.decode(response.responseText);
					var info = JSON.parse(data.data).data;
					store.loadData(info.campusList);
					field.setValue(data.clientCode != '0508001' ? data.clientCode : store.first());
					if(statistic == true) {
						field.setReadOnly(data.clientCode == '0508001' ? false : true);
					}else {
						field.setReadOnly(true);
					}
					deferred.resolve();
				},
				error : function(response) {
					if( checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut();
					}
					deferred.reject({message: 'error'});
				},
				failure: function(response){
					if( checkAjaxSessionTimeOut(response)){
						redirectedWhenSessionTimeOut();
					}
					deferred.reject({message: 'failure'});
				}
			});
			if(callBackFunction != undefined) {
				callBackFunction.call(this);
			}
			return deferred.promise;
		},
		
		getCampusListTreeStore: function(store, tree){
			Ext.Ajax.request({
				url : CONTEXT_PATH + "/routineSetting/getCampusListTree.json",
				params : {},
				success : function(response) {
					if(checkAjaxSessionTimeOut(response)) {
						redirectedWhenSessionTimeOut()
						return;
					}
					var json = Ext.decode(response.responseText);
					var campusList = json.data;
					var campusGbnList = [];
					var currentCampus = json.currentClientCode;
					Ext.each(campusList, function(item) {
						if(_.find(campusGbnList, {campusGbn: item.campusGbn}) == null) {
							campusGbnList.push(item);
						}
					})
					var root = {
						text: 'Root',
						id: 'root',
						expanded: true,
						checked: false,
						children: []
					};
					var count = -1;
					Ext.each(campusGbnList, function(item) {
						root.children.push({
							id: item.campusGbn, 
							text: item.campusGbnName, 
							leaf: false, 
							checked: false, 
							children: []
						});
						count++;
						Ext.each(campusList, function(record) {
							if (record.campusGbn == item.campusGbn) {
								root.children[count].children.push({
									id: record.clientCode, 
									text: record.clientName, 
									checked: false, 
									leaf: true
								})
							}
						});
					})
					store.setRoot(root);
					if (currentCampus == '0508001') {
						tree.setValue('0508003');
					} else {
						tree.setValue(currentCampus);
					}
					tree.setReadOnly(currentCampus == '0508001' ? false : true);
					tree.okFunction();
				},
			});
		},
		
//	END
	}
})
