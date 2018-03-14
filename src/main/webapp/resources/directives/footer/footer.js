'use strict';

angular.module('DataMiningCGE').directive('footer',function(){
	return {
		templateUrl: 'resources/directives/footer/footer.html',
		restrict: 'E',
        replace: true
	}
});