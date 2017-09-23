"use strict";

console.log('Hello from JS file');

var a = 1;
console.log('a=', a);
console.log('window.a=', window.a);

console.log('document=', document);
console.log('window=', window);

//Types:

//Number
console.log('typeof 1', typeof 1);
console.log('typeof 1.2', typeof 1.2);
console.log('1 / 0 == Infinity', -1 / 0 == Infinity );
console.log('0 / 0', 0 / 0);

//Boolean
console.log('typeof true', typeof true);
var bool = false;
console.log('typeof bool', typeof bool);

//String
console.log('typeof ""', typeof "");
console.log("typeof ''", typeof 'a');

//Object
var obj = {
	message: 'Hello', 
	name: 'John',
	greet: function() {
		return this.message + ', ' + this.name + '!';
	}
}
console.log("obj=", obj);
console.log("obj.name=", obj.name);
console.log("obj['name']", obj['name']);

for(var key in obj) {
	console.log("   " + key + "=", obj[key]);
}

var map = {};
var key = 'name';
var value = 'John';
map[key] = value;
console.log("map=", map);


console.log( "obj.greet()=", obj.greet() );
console.log("typeof {}", typeof obj);
console.log("typeof null", typeof null);

if ( typeof obj === 'object' ) {
	console.log('obj is an object');
}

//Function
console.log("sum(2,3)=", sum(2,3) );

function sum(a, b) {
	return a + b;
}

var s = sum;
console.log("s(2,3)=", s(2,3) );

var mult = function(a, b) {
	return a*b;
}

console.log("mult(2,3)=", mult(2,3) );
console.log("typeof mult", typeof mult);

//Undefiend
console.log("typeof mult", typeof undefined);
var u;
console.log("typeof u", typeof u);

//
console.log("true == 1", true == 1);
console.log("true == 0", true == 0);

console.log("true === 1", true === 1);

console.log("true == '1'", 1 == '1');
console.log("true === '1'", 1 === '1');


console.log('"" == false', "" == false);
var s = "";
if(s) {
	console.log("if(s)");
}

//Arrays
var a = [];
a = [1,2,3];
a.push(5);
console.log("a=", a);
console.log("a.join()", a.join(' '));
console.log("'1.2.3.4'.split('.')", '1.2.3.4'.split('.'));

console.log({0:2});
//Functions +
function showMessage(msg = 'Hello') {
	//var message = msg || 'Hello';
	console.log('showMessage:' + msg, arguments);
}

showMessage();
showMessage('Hi', 2);

function makeCounter() {
	var currentCount = 0;
	return function() {
	    return ++currentCount;
	};
}

var counter = makeCounter();

console.log('counter', counter() );
console.log('counter', counter() );

console.log(abc);
var abc;




