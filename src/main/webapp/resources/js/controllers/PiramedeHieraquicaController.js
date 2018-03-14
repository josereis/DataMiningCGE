angular.module('DataMiningCGE').controller('PiramedeHieraquicaController', function($scope, PiramedeHieraquicaService) {
	var datas = [];
	
	$scope.exibeInputOrgao = function() {
		return ($scope.selectType=="orgao") ? true : false;
	}
	
	$scope.exibeInputCategoria = function() {
		return ($scope.selectType=="categoria") ? true : false;
	}
	
	$scope.exibeGrafico = function() {
		if(($scope.selectType=="orgao")&&($scope.selectDate!="")) {
			if(($scope.orgao_1!=undefined)&&($scope.orgao_2!=undefined)&&($scope.orgao_3!=undefined)) {
				datas['input1'] = $scope.orgao_1;
				datas['input2'] = $scope.orgao_2;
				datas['input3'] = $scope.orgao_3;
				datas['tipo'] = $scope.selectType;
				datas['referencia'] = $scope.selectDate;
				
				PiramedeHieraquicaService.requisicaoPiramede(datas).success(function(data) {
					// se sucesso cria o grafico
					//Create the chart
					Highcharts.mapChart('grafico', {
						chart: {
				            type: 'pyramid',
				            marginRight: 100
				        },
				        title: {
				            text: 'Piramide Hierarquica',
				            x: -50
				        },
				        plotOptions: {
				            series: {
				                dataLabels: {
				                    enabled: true,
				                    format: '<b>{point.name}</b> ({point.y:,.0f})',
				                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
				                    softConnector: true
				                }
				            }
				        },
				        legend: {
				            enabled: false
				        },
				        series: data
					});
					
				}).error(function(data, status) {
					$state.go('erro', {}, {reload: true});
				});
			}
			return true;
		} else if(($scope.selectType=="categoria")&&($scope.selectDate!="")) {
			if(($scope.categoria_1!=undefined)&&($scope.categoria_2!=undefined)&&($scope.categoria_3!=undefined)) {
				datas['input1'] = $scope.categoria_1;
				datas['input2'] = $scope.categoria_2;
				datas['input3'] = $scope.categoria_3;
				datas['tipo'] = $scope.selectType;
				datas['referencia'] = $scope.selectDate;
				
				PiramedeHieraquicaService.requisicaoPiramede(datas).success(function(data) {
					// se sucesso cria o grafico
					//Create the chart
					Highcharts.mapChart('grafico', {
						chart: {
				            type: 'pyramid',
				            marginRight: 100
				        },
				        title: {
				            text: 'Piramide Hierarquica',
				            x: -50
				        },
				        plotOptions: {
				            series: {
				                dataLabels: {
				                    enabled: true,
				                    format: '<b>{point.name}</b> ({point.y:,.0f})',
				                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
				                    softConnector: true
				                }
				            }
				        },
				        legend: {
				            enabled: false
				        },
				        series: data
					});
				}).error(function(data, status) {
					$state.go('erro', {}, {reload: true});
				});
			}
			return true;
		} else {
			return false;
		}
	}
	
});