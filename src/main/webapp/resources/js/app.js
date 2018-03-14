var app = angular.module('DataMiningCGE', [ 'ngResource', 'ui.router',
		'ui.bootstrap', 'ngCookies' ]);

app.config([ '$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider', function($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {
	
	$locationProvider.html5Mode(false);
	
	$urlRouterProvider.otherwise('/main');

	$stateProvider.state('main', {
		url : '/main',
		templateUrl : 'views/main'
	}).state('mapa', {
		url : '/main/mapa-demografico-servidores',
		templateUrl : 'views/mapa',
		controller : 'MapaDemograficoController'
	}).state('piramede', {
		url : '/main/piramede-hieraquica-servidores',
		templateUrl : 'views/piramede',
		controller : 'PiramedeHieraquicaController'
	});

	// Enable cross domain calls
	$httpProvider.defaults.useXDomain = true;
	// Remove the header used to identify ajax call that would prevent
	// CORS from working
	delete $httpProvider.defaults.headers.common['X-Requested-With'];
} ]);
