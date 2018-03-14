<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<base href="/DataMiningCGE/">
		<title>DataMiningCGE</title>
		
		<!-- ######################### LIBRARIES #########################-->
		<script type="text/javascript" src="resources/lib/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="resources/lib/angular/angular.js"></script>
		<script type="text/javascript" src="resources/lib/angular/angular-ui-router.min.js"></script>
		<script type="text/javascript" src="resources/lib/angular/angular-resource.min.js"></script>
		<script type="text/javascript" src="resources/lib/angular/angular-cookies.min.js"></script>
		<script type="text/javascript" src="resources/lib/bootstrap/bootstrap.js"></script>
		<script type="text/javascript" src="resources/lib/bootstrap/ui-bootstrap-tpls.min.js"></script>
		<script type="text/javascript" src="resources/lib/metisMenu/metisMenu.min.js"></script>
		<script type="text/javascript" src="resources/lib/sb-admin-2/sb-admin-2.js"></script>
		
		<script type="text/javascript" src="resources/js/app.js"></script>

		<link rel="stylesheet" type="text/css" href="resources/css/app.css">		
		<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="resources/lib/sb-admin-2/sb-admin-2.css">
		<link rel="stylesheet" type="text/css" href="resources/lib/metisMenu/metisMenu.min.css" >
		<link rel="stylesheet" type="text/css" href="resources/lib/font-awesome/css/font-awesome.min.css" >
		
		<!-- ######################### CONTROLLERS #########################-->
		<script type="text/javascript" src="resources/js/controllers/HomeController.js"></script>
		<script type="text/javascript" src="resources/js/controllers/MapaController.js"></script>
		<script type="text/javascript" src="resources/js/controllers/PiramedeHieraquicaController.js"></script>
		
		<!-- ########################### SERVICES ##########################-->
		<script type="text/javascript" src="resources/js/services/HomeService.js"></script>
		<script type="text/javascript" src="resources/js/services/MapaService.js"></script>
		<script type="text/javascript" src="resources/js/services/PiramedeHieraquicaService.js"></script>
		
		<script src="https://code.highcharts.com/maps/highmaps.js"></script>
		<script src="https://code.highcharts.com/maps/modules/exporting.js"></script>
		<script src="https://code.highcharts.com/mapdata/countries/br/br-all.js"></script>

	</head>
	
	<body ng-app="DataMiningCGE">
		<header>
			<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
				<div class="container">
					 <div class="navbar-header">
		                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
		                    <span class="sr-only">Toggle navigation</span>
		                    <span class="icon-bar"></span>
		                    <span class="icon-bar"></span>
		                    <span class="icon-bar"></span>
		                </button>
		                <a class="navbar-brand" href="">DataMiningCGE</a>
		            </div>
					
					<div id="navbar" class="navbar-collapse collapse pull-right">
						<ul id="navbar" class="nav navbar-top-links navbar-right">
							<li class="nav-item">
								<a ui-sref="main" class="nav-link">Home</a>
							</li>
							
							<li class="nav-item">
								<a ui-sref="#" class="nav-link">Sobre</a>
							</li>
							
							<li class="nav-item">
								<a ui-sref="#" class="nav-link">Suporte</a>
							</li class="nav-item">
							
							<li class="nav-item">
								<a ui-sref="#" class="nav-link">Contato</a>
							</li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
		
		<main role="main">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-md-3">
						<div class="navbar sidebar" role="navigation">
							<button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#sidemenu" aria-controls="sidemenu" aria-expanded="false" aria-label="Toggle navigation">
								<span class="navbar-toggler-icon">Consult</span>
							</button>
							
							<div id="sidemenu" class="sidebar-nav navbar-collapse collapse">
								<ul class="nav">
									<li>
										<a ui-sref="#">Faixa Etaria</a>
									</li>
									
									<li>
										<a ui-sref="#">Soma Salarial</a>
									</li>
									
									<li>
										<a ui-sref="#">Media Salarial</a>
									</li>
										
									<li>
										<a ui-sref="#">Dispersao Salarial</a>
									</li>
									
									<li>
										<a ui-sref="mapa">Mapa Demografico dos Servidores</a>
									</li>
									
									<li>
										<a ui-sref="#">Distribuicao dos Gastos da Folha</a>
									</li>
										
									<li>
										<a ui-sref="piramede">Piramide Hierarquica das Categorias</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
					<div class="col-md-9">
						<div ui-view></div>
					</div>
				</div>
			</div>
		</main>
		
		<footer class="footer">
			<div class="container">
				<span class="text-muted">
					<p class="pull-left">
						&copy; 2017 Universidade Federal do Piaui, UFPI. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a>
					</p>
					
					<p class="pull-right">
						&copy; 2017 Universidade Federal do Piaui, UFPI. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a>
					</p>
<!-- 					Place sticky footer content here. -->
				</span>
			</div>
		</footer>
		
		<script>
		 	$(function() {
		 		$('#sidemenu').metisMenu();
		 	});
		</script>
	</body>
</html>