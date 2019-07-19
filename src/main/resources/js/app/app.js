'use strict'

var demoApp = angular.module('demo', [ 'ui.bootstrap', 'demo.controllers',
		'demo.services' ]);
demoApp.constant("CONSTANTS", {
	getApplicantByIdUrl : "/user/getUser/",
	getAllUsers : "/user/getAllUsers",
	saveUser : "/user/saveUser"
});