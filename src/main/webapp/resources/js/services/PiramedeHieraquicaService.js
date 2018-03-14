angular.module("DataMiningCGE").service("PiramedeHieraquicaService", ["$http", '$q', "$cookieStore", function ($http, $q, $cookieStore){
	
	function requisicaoPiramede(datas) {
		return $http.post("http://localhost:8080/DataMiningCGE/backend/piramide", JSON.stringify(datas));
	}
	
}]);