angular.module('DataMiningCGE').controller('MapaDemograficoController', function($scope, MapaService) {
	//Prepare demo data
	//Data is joined to map using value of 'hc-key' property by default.
	//See API docs for 'joinBy' for more info on linking data and map.
	var data = [
		 ['br-sp', 82],
		 ['br-ma', 173],
		 ['br-pa', 2],
		 ['br-sc', 0],
		 ['br-ba', 10],
		 ['br-ap', 0],
		 ['br-ms', 0],
		 ['br-mg', 14],
		 ['br-go', 15],
		 ['br-rs', 1],
		 ['br-to', 3],
		 ['br-pi', 109593],
		 ['br-al', 2],
		 ['br-pb', 5],
		 ['br-ce', 89],
		 ['br-se', 2],
		 ['br-rr', 3],
		 ['br-pe', 34],
		 ['br-pr', 1],
		 ['br-es', 4],
		 ['br-rj', 43],
		 ['br-rn', 4],
		 ['br-am', 5],
		 ['br-mt', 4],
		 ['br-df', 143],
		 ['br-ac', 0],
		 ['br-ro', 3]
	];
	
	//Create the chart
	Highcharts.mapChart('container', {
	 chart: {
	     map: 'countries/br/br-all'
	 },
	
	 title: {
	     text: 'Mapa Demográfico'
	 },
	
	 subtitle: {
	     text: 'Mapa Demografico dos Sevidores Públicos Estaduais do Piauí'
	 },
	
	 mapNavigation: {
	     enabled: true,
	     buttonOptions: {
	         verticalAlign: 'bottom'
	     }
	 },
	
	 colorAxis: {
	     min: 0
	 },
	
	 series: [{
	     data: data,
	     name: 'Random data',
	     states: {
	         hover: {
	             color: '#BADA55'
	         }
	     },
	     dataLabels: {
	         enabled: true,
	         format: '{point.name}'
	     }
	 }]
	});
});