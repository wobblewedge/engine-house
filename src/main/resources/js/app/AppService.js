'use strict'

angular.module('demo.services', []).factory('AppService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};
			service.getApplicantById = function(id) {
				var url = CONSTANTS.getApplicantById + id;
				return $http.get(url);
			}
			service.getAllUsers = function() {
				return $http.get(CONSTANTS.getAllUsers);
			}
			service.saveUser = function(userDto) {
				return $http.post(CONSTANTS.saveUser, applicantDto);
			}
			return service;
		} ]);