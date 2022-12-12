
function handleException(e) {
	if(e.info != '' && e.info != null) {
		showMessageBoxError(e.info.message);
	}
	console.log(e.info)
	console.log(e.stack)
}

function showMessageSaveSuccess(){
	Ext.MessageBox.show({
		cls : 'messageBoxTextCenter',
		title : 'KIS',
		msg :'Saved data successfully',
		buttons : Ext.MessageBox.YES,
		icon : Ext.MessageBox.INFO
	});
}

function showMessageBoxError(message){
	Ext.MessageBox.show({
		cls : 'messageBoxTextCenter',
		title : 'KIS',
		msg : message,
		buttons : Ext.MessageBox.YES,
		icon : Ext.MessageBox.ERROR
	});
}

function getDataAjax(ajaxUrl, params) {
	let deferred = new Ext.Deferred();
	mask();
	Ext.Ajax.request({
		url : CONTEXT_PATH + ajaxUrl,
		jsonData : params,
		timeout: 10000,
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
			'Authorization':'Basic xxxxxxxxxxxxx',
		},
		success : function(response) {
			var data = Ext.decode(response.responseText);
			deferred.resolve(data);
			unMask();
		},
		error : function(response) {
			unMask();
			deferred.reject({info: Ext.decode(response.responseText)});
		},
		failure: function(response){
			unMask();
			deferred.reject({info: Ext.decode(response.responseText)});
		}
	});
	return deferred.promise;
}

function mask() {
	Ext.getBody().addCls('parentMask'); 
	Ext.getBody().mask("Loading...", "x-mask-loading", false);
}
function unMask() {
	Ext.getBody().removeCls('parentMask');
	Ext.getBody().unmask();
}

function getFormField(form, field) {
	return form.getForm().findField(field);
}

function getStoreOpt(isAddNewOption, newDisplayField, newValueField, isSelectedValue, selectedValue, isSelectFirst) {
	return {
		isAddNewOption 	: isAddNewOption,
		newDisplayField : newDisplayField,
		newValueField 	: newValueField,
		isSelectedValue : isSelectedValue,
		selectedValue 	: selectedValue,
		isSelectFirst	: isSelectFirst
	}
}

function formatSizeUnits(bytes){
	if      (bytes>=1073741824) {bytes=(bytes/1073741824).toFixed(2)+' GB';}
	else if (bytes>=1048576)    {bytes=(bytes/1048576).toFixed(2)+' MB';}
	else if (bytes>=1024)       {bytes=(bytes/1024).toFixed(2)+' KB';}
	else if (bytes>1)           {bytes=bytes+' bytes';}
	else if (bytes==1)          {bytes=bytes+' byte';}
	else                        {bytes='0 byte';}
	return bytes;
}

function setParamsToProxy(params, proxy) {
	for (const key of Object.keys(params)) {
	    const value = params[key];
	    proxy.setExtraParam(key, value);
	}
}
function formatNumber(value, decimalPlaces) {
	return Number(Math.round(value + 'e' + decimalPlaces) + 'e-' + decimalPlaces);
}
