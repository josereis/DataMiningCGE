'use strict';

angular.module('DataMiningCGE').directive('header',function(){
	return {
		templateUrl: 'resources/directives/header/header.html',
		restrict: 'E',
        replace: true
	}
});