'use strict'

var module = angular.module('demo.controllers', []);
module.controller("ApplicantController", [ "$scope", "AppService",
		function($scope, AppService) {

			$scope.applicantDto = {
				id : 0,
				name : null,
				address: null,
				age: 0,
				income: 0,
				debts: 0,
				assets: 0,
				credit: 0,
				approval: false
			};
			
			AppService.getApplicantById(1).then(function(value) {
				console.log(value.data);
			}, function(reason) {
				console.log("error occured");
			}, function(value) {
				console.log("no callback");
			});

			
				AppService.saveUser($scope.applicantDto).then(function() {
					console.log("works");
					AppService.getAllUsers().then(function(value) {
						$scope.allUsers= value.data;
					}, function(reason) {
						console.log("error occured");
					}, function(value) {
						console.log("no callback");
					});

					$scope.applicantDto = {
							id : 0,
							name : null,
							address: null,
							age: 0,
							income: 0,
							debts: 0,
							assets: 0,
							credit: 0,
							approval: false
					};
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
		} ]);