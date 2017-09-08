"use strict";

function showUsers() {
	
	var title = document.getElementsByTagName('title')[0];
	title.innerHTML = "Users";
	
	document.getElementById('message').innerHTML="Loading users...";
	
	var usersTable = document.getElementById('usersTable');
	if (usersTable) {
		document.body.removeChild(usersTable);
	}
	
	fetchUsersPage( function(response) {
		document.getElementById('message').innerHTML = "";
		var usersTable = document.getElementById('usersTable');
		if (usersTable) {
			document.body.removeChild(usersTable);
		}
		renderUsers(response.items);		
	});
}

function showProducts() {
	
	var title = document.getElementsByTagName('title')[0];
	title.innerHTML = "Products";
	
	fetchUsersPage( function(response) {		
		renderUsers(response.items);
		
	});
}

//var users = fetchUsersPage().items;
//renderUsers(users);

function renderUsers(users) {
	var body = document.getElementsByTagName('body')[0];
	var table = document.createElement('table');

	table.setAttribute('class', 'table table-striped table-hover');
	table.setAttribute('id', 'usersTable');

	var thead = document.createElement('thead');

	var thFirstName = document.createElement('th');
	thFirstName.innerHTML = "First Name";
	thead.appendChild(thFirstName);

	var thLastName = document.createElement('th');
	thLastName.innerHTML = "Last name";
	thead.appendChild(thLastName);

	table.appendChild(thead);

	var tbody = document.createElement('tbody');
	table.appendChild(tbody);

	for (var i = 0; i < users.length; i++) {
		var user = users[i];
		var tr = document.createElement('tr');

		var tdFirstName = document.createElement('td');
		tdFirstName.innerHTML = user.firstName;
		tr.appendChild(tdFirstName);

		var tdLastName = document.createElement('td');
		tdLastName.innerHTML = user.lastName;
		tr.appendChild(tdLastName);

		tbody.appendChild(tr);

	}

	body.appendChild(table);
}

function fetchUsersPage(onFetchDone) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var response = JSON.parse(this.responseText);
	    	onFetchDone(response);
	    }
	};
	xhttp.open("GET", "shop/users", true);
	xhttp.send();
	
//	return {
//			filter: {
//				start: 0,
//				count: 10,
//				lastName: null,
//				email: null,
//				params: ""
//			},
//			items: [
//			{
//				id: 18,
//				firstName: "Alexander",
//				lastName: "Medvedev",
//				email: "alex-medvedev@tut.by",
//				phone: "+375(29)7505384",
//				created: 1497698249000,
//				updated: 1501305065000,
//				adresses: null
//			},
//			{
//			id: 19,
//			firstName: "SERGEY",
//			lastName: "TSAPOVSKI",
//			email: "TSAPOVSKI@YANDEX.RU",
//			phone: "232454345",
//			created: 1497698439000,
//			updated: 1500713646000,
//			adresses: null
//			},
//			{
//			id: 20,
//			firstName: "Sergey",
//			lastName: "Morozov",
//			email: "smarozau@gmail.com",
//			phone: "+375295566078",
//			created: 1497698507000,
//			updated: 1497698507000,
//			adresses: null
//			},
//			{
//			id: 22,
//			firstName: "Dmitry",
//			lastName: "Tsapovski",
//			email: "tsapovski@gmail.com",
//			phone: "+375296592016",
//			created: 1497698572000,
//			updated: 1497698572000,
//			adresses: null
//			},
//			{
//			id: 24,
//			firstName: "Vladimir",
//			lastName: "Yashchenko",
//			email: "yashchenko-vladimir@tut.by",
//			phone: "+375295773281",
//			created: 1497698637000,
//			updated: 1497698637000,
//			adresses: null
//			},
//			{
//			id: 139,
//			firstName: "Nola",
//			lastName: "Lopez",
//			email: "cursus.Nunc@vehicula.edu",
//			phone: "+375 29 331 17 12",
//			created: 1504257876000,
//			updated: 1504257876000,
//			adresses: null
//			},
//			{
//			id: 140,
//			firstName: "Kevin",
//			lastName: "Walters",
//			email: "amet.massa@Pellentesque.ca",
//			phone: "+375 78 441 74 32",
//			created: 1504257876000,
//			updated: 1504257876000,
//			adresses: null
//			},
//			{
//			id: 141,
//			firstName: "Carl",
//			lastName: "Henson",
//			email: "turpis.non.enim@Maurismolestiepharetra.co.uk",
//			phone: "+375 86 966 90 11",
//			created: 1504257876000,
//			updated: 1504257876000,
//			adresses: null
//			},
//			{
//			id: 142,
//			firstName: "Kim",
//			lastName: "Grimes",
//			email: "sagittis@tristique.net",
//			phone: "+375 30 742 61 54",
//			created: 1504257876000,
//			updated: 1504257876000,
//			adresses: null
//			},
//			{
//			id: 143,
//			firstName: "Rafael",
//			lastName: "Barker",
//			email: "orci.lobortis@tinciduntvehicula.org",
//			phone: "+375 75 966 13 25",
//			created: 1504257876000,
//			updated: 1504257876000,
//			adresses: null
//			}
//			],
//			totalItemsCount: 105,
//			totalPagesCount: 11
//	};
}

