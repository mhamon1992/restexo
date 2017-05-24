<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<input id="action" type="submit" value="appuyer">
		<textarea id="textarea"></textarea>
		
		<label>nom</label>
		<input id="nom" name = "nom" type="text">
		<label>prenom</label>
		<input id="prenom" name ="prenom" type="text">
		<label>numero</label>
		<input id="numero"name = "numero"type="text">
		<input id ="ajouter" type="submit" value ="button">
		
		<!-- <script type="text/javascript" src="afficher.js"></script> -->
		 <script src="jquery-3.2.1.js"></script>
		 <script type="text/javascript">	 
		  $(function() {
        	$('#action').click(function() {
	          $.getJSON('http://localhost:8080/restex/rest/hello/contacts', function(data) {
	        	  var val = JSON.stringify(data);
	        	  /* var pos = val.indexOf("{");
	        	  var position;
	        	  var count = 0;
	        	  var res ="";
	        	  while(pos != -1){
	        		  position[count] = val.indexOf("{", pos+1);
	        		  count++;
	        	  }
	        	  for(var i =0; i < position.length(); i++){
	        		  if(i === 0){
	        			  res += val.substring(i,position[i])+"\n";
	        		  }else{
	        			  res += val.substring(position[i],position[i+1])+"\n";
	        		  }	  
	        	  } */
	        	  /* var tab = val.split(','); 
	        	  var res = "";
	        	  for(var value in tab){
	        	  	res+= tab[value]+"\n";
	        	  }

	        	  $("#textarea").html(res); */
	        	  $("#textarea").html(val);
	        	  //alert(val);
	        	  console.log($('#textarea').html());
	        	  //console.log(res);
	        	  //$("#textarea").val(val); 
	          });    
       	 	});  
      	  });
		  $(function() {
	        	$('#ajouter').click(function() {
	        		var data = {
	        			"nom":$("#nom").val(),
	        			"prenom": $("#prenom").val(),
	        			"numero": $("#numero").val()
	        		}
	        		$.ajax({
	        			headers:{
	        				"accept": "application/json",
	        				'content-type':"application/json"
	        			},
	        		       "url" : 'http://localhost:8080/restex/rest/hello/creerContact/',
	        		       "type" : 'POST', // Le type de la requête HTTP, ici devenu POST
	        		       "data" : JSON.stringify(data), // On fait passer nos variables, exactement comme en GET, au script more_com.php
	        		       "dataType" : 'json'
	        		    });
	        	});  
      	  });
		 
		          
		  </script>
</body>
</html>