"use strict";

function formatUserNo(item) {
	return '<a href="admin/user?userId=' + item.id + '">' + item.id + '</a>';
}

function showUsers() {
	debugger;
	var usersPanel = document.getElementById('usersPanel');
	usersPanel.setAttribute('style', '');
	fetchUsers(function(response) {
		//renderUsers(response.items);
		renderTable(
			'usersTable', 
			'usersPanelBody', 
			 { 
				fields: [
					{name: 'id', title: '#', format: formatUserNo },
					{name: 'firstName', title: 'First name'}, 
					{name: 'lastName', title: 'Last name'},
					{name: 'email', title: 'Email'},
					{name: 'phone', title: 'Phone'}
				]
			 },
			 response.items);
	});	
}

/*
 * renderTable('usersTable', 'usersPanelBody', 
 * 	{ fields: [{name: 'firstName', title: 'First name'}, {name: 'lastName', title: 'Last name'}]},
 *  users); 
 */

function renderTable(id, parentId, schema, items) {
	debugger;
	var body = document.body; // document.getElementsByTagName('body')[0];
	
	var parent = document.getElementById(parentId);
	
	var table = document.getElementById(id);
	if (table) {
		parent.removeChild(table);
	}
	
	table = document.createElement('table');
	table.setAttribute('class', 'table table-striped table-hover');
	table.setAttribute('id', id);
	parent.appendChild(table);

	var thead = document.createElement('thead');
	table.appendChild(thead);

	var trh = document.createElement('tr');
	thead.appendChild(trh);
	for (var i = 0; i < schema.fields.length; i++) {
		var field = schema.fields[i];	
		var th = document.createElement('th');
		th.innerText = field.title;
		trh.appendChild(th);
	}	
	
	var tbody = document.createElement('tbody');
	table.appendChild(tbody);

	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var tr = document.createElement('tr');
		tbody.appendChild(tr);
		for (var j = 0; j < schema.fields.length; j++) {
			var field = schema.fields[j];	
			var tdFN = document.createElement('td');
			tdFN.innerHTML = field.format ? field.format(item) : item[field.name];
			tr.appendChild(tdFN);
		}		
	}
}

function renderUsers(users) {
	
	var body = document.body; // document.getElementsByTagName('body')[0];
	
	var usersPanelBody = document.getElementById('usersPanelBody');
	
	var table = document.getElementById('usersTable');
	if (table) {
		usersPanelBody.removeChild(table);
	}
	
	table = document.createElement('table');
	table.setAttribute('class', 'table table-striped table-hover');
	table.setAttribute('id', 'usersTable');
	usersPanelBody.appendChild(table);

	var thead = document.createElement('thead');
	table.appendChild(thead);

	var trh = document.createElement('tr');
	thead.appendChild(trh);
	var thFirstName = document.createElement('th');
	thFirstName.innerText = "First name";
	trh.appendChild(thFirstName);
	var thLastName = document.createElement('th');
	thLastName.innerText = "Last name";
	trh.appendChild(thLastName);

	var tbody = document.createElement('tbody');
	table.appendChild(tbody);

	for (var i = 0; i < users.length; i++) {
		var user = users[i];
		var tr = document.createElement('tr');
		tbody.appendChild(tr);
		var tdFN = document.createElement('td');
		tdFN.innerText = user.firstName;
		tr.appendChild(tdFN);
		var tdLN = document.createElement('td');
		tdLN.innerText = user.lastName;
		tr.appendChild(tdLN);
	}
}

function fetchUsers(onFetchDone) {
	var usersPanelBody = document.getElementById('usersPanelBody');
	usersPanelBody.innerHTML = "Loading..."
	var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			usersPanelBody.innerText = "";
	    	var response = JSON.parse(this.responseText);
	    	onFetchDone(response);	    	
	    }
	};
	
	xhttp.open("GET", "shop/users", true);
	xhttp.send();
}
