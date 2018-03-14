<div class="panel panel-default">
	<div class="panel-body">
		<form class="was-validated">
			<div class="form-group col-lg-3">
				<label for="selectDate" class="text-center">Selecione o Ano</label>
				<select class="form-control" id="selectDate" ng-model="selectDate" required>
					<option id="vazio" value="">...</option>
					<option id="data_2014" value="2014">2014</option>
					<option id="data_2015" value="2015">2015</option>
					<option id="data_2016" value="2016">2016</option>
				</select>
			
				<label for="selectType" class="text-center">Selecione o tipo</label>
				<select class="form-control" id="selectType" ng-model="selectType" name="selectType" required>
					<option value="">...</option>
					<option value="orgao">Orgão</option>
					<option value="categoria">Categoria</option>
				</select>
			</div>
			
			<div id="input_categoria" class="form-group col-lg-3" ng-show="exibeInputCategoria()">
				<label for="categoria_1" class="text-center">1ª Categoria</label>
				<input type="text" ng-model="categoria_1" class="form-control" id="categoria_1" aria-describedby="Primeira Categoria" placeholder="Categoria" required />
				
				<label for="categoria_2" class="text-center">2ª Categoria</label>
				<input type="text" ng-model="categoria_2" class="form-control" id="categoria_2" aria-describedby="Segunda Categoria" placeholder="Categoria" required />
				
				<label for="categoria_3" class="text-center">3ª Categoria</label>
				<input type="text" ng-model="categoria_3" class="form-control" id="categoria_3" aria-describedby="Terceira Categoria" placeholder="Categoria" required />
			</div>
			
			<div id="input_orgao" class="form-group col-lg-3" ng-show="exibeInputOrgao()">
				<label for="orgao_1" class="text-center">1ª Orgão</label>
				<input type="text" ng-model="orgao_1" class="form-control" id="orgao_1" aria-describedby="Primeiro Orgão" placeholder="orgao" required />
				
				<label for="orgao_1" class="text-center">2ª Orgão</label>
				<input type="text" ng-model="orgao_2" class="form-control" id="orgao_2" aria-describedby="Segundo Orgão" placeholder="orgao" required />
				
				<label for="orgao_1" class="text-center">3ª Orgão</label>
				<input type="text" ng-model="orgao_3" class="form-control" id="orgao_3" aria-describedby="Terceiro Orgão" placeholder="orgao" required />
			</div>
			
			<div id="gifCarregando" class="col-lg-3" style="display:none">
				<img src='http://i.kinja-img.com/gawker-media/image/upload/chag4hzw0pgvgy5ujnom.gif' />
			</div>
		</form>
	</div>
	
	<div class="panel-body">
		<div id="grafico"></div>
	</div>
	
	<div class="panel-body">
		<div id="gifGrafico" style="display:none">
			<img src='http://i.kinja-img.com/gawker-media/image/upload/chag4hzw0pgvgy5ujnom.gif' />
		</div>
	</div>
</div>