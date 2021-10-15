//Como usuarie de QueMePongo quiero ver todas las prendas que tengo en mi guardarropas desde el navegador para poder administrarlas
curl 'https://macowins-server.herokuapp.com/guardarropas_id/prendas
200 OK
204 No Content
body {[
  {
    "id": 1,
    "tipoPrenda": "camisa", 
    "material": "tela", 
    "colorPrimario": "celeste", 
    "trama": "rayada", 
    "temperaturaAdecuada": 22
  },
  {
    "id": 2,
    "tipoPrenda": "remera", 
    "material": "tela", 
    "colorPrimario": "amarillo", 
    "trama": "liso", 
    "temperaturaAdecuada": 15
  },
  {
    "id": 3,
    "tipoPrenda": "zapatillas", 
    "material": "cuero", 
    "colorPrimario": "blanco", 
    "trama": "liso", 
    "temperaturaAdecuada": 15
  }
  
]}

//Como usuario de QueMePongo, quiero crear una prenda desde el navegador
curl -XPOST 'https://macowins-server.herokuapp.com/guardarropas_id/prendas/' --data '{ "tipoPrenda": "chomba","material": "tela", "colorPrimario": "rojo", "trama": "rayada", "temperaturaAdecuada": 22 }' -H 'Content-Type:application/json'
200 OK
403 Forbidden
body {[ {
    "id": 1,
    "tipoPrenda": "camisa", 
    "material": "tela", 
    "colorPrimario": "celeste", 
    "trama": "rayada", 
    "temperaturaAdecuada": 22
  },
  {
    "id": 2,
    "tipoPrenda": "remera", 
    "material": "tela", 
    "colorPrimario": "amarillo", 
    "trama": "liso", 
    "temperaturaAdecuada": 15
  },
  {
    "id": 3,
    "tipoPrenda": "zapatillas", 
    "material": "cuero", 
    "colorPrimario": "blanco", 
    "trama": "liso", 
    "temperaturaAdecuada": 15
  },
  {
    "id": 4,
    "tipoPrenda": "chomba", 
    "material": "tela", 
    "colorPrimario": "rojo", 
    "trama": "rayada", 
    "temperaturaAdecuada": 22  
]}


//Como usuarie de QueMePongo quiero ver una prenda en particular que tengo en mi guardarropas para poder editarla
curl -XGET 'https://macowins-server.herokuapp.com/guardarropas_id/prendas/4'
200 OK
204 No Content
body {[
  {
    "id": 4,
    "tipoPrenda": "chomba", 
    "material": "tela", 
    "colorPrimario": "rojo", 
    "trama": "rayada", 
    "temperaturaAdecuada": 22  
]}


//Como usuarie de QueMePongo, quiero poder eliminar una prenda de mi guardarropas
curl -XDELETE 'https://macowins-server.herokuapp.com/guardarropas_id/prendas/4'
200 OK
403 Forbidden
body {[
  {
    "id": 1,
    "tipoPrenda": "camisa", 
    "material": "tela", 
    "colorPrimario": "celeste", 
    "trama": "rayada", 
    "temperaturaAdecuada": 22
  },
  {
    "id": 2,
    "tipoPrenda": "remera", 
    "material": "tela", 
    "colorPrimario": "amarillo", 
    "trama": "liso", 
    "temperaturaAdecuada": 15
  },
  {
    "id": 3,
    "tipoPrenda": "zapatillas", 
    "material": "cuero", 
    "colorPrimario": "blanco", 
    "trama": "liso", 
    "temperaturaAdecuada": 15
  }
]}


//Como usuarie de QueMePongo, quiero poder ver mis eventos para administrarlos
curl -XPUT 'https://macowins-server.herokuapp.com/guardarropas_id/prendas/2' --data '{"tipoPrenda": "pantalon","material": "denim", "colorPrimario": "azul", "trama": "liso", "temperaturaAdecuada": 22  }' -H 'Content-Type:application/json'
200 OK
201 Created
403 Forbidden
body {[
  {
    "id": 1,
    "tipoPrenda": "camisa", 
    "material": "tela", 
    "colorPrimario": "celeste", 
    "trama": "rayada", 
    "temperaturaAdecuada": 22
  },
  {
    "id": 2,
    "tipoPrenda": "pantalon", 
    "material": "denim", 
    "colorPrimario": "azul", 
    "trama": "liso", 
    "temperaturaAdecuada": 22
  },
  {
    "id": 3,
    "tipoPrenda": "zapatillas", 
    "material": "cuero", 
    "colorPrimario": "blanco", 
    "trama": "liso", 
    "temperaturaAdecuada": 15
  }
]}


//Como usuarie de QueMePongo, quiero poder abrir las sugerencias de prendas para un evento en mi navegador
curl -XGET 'https://macowins-server.herokuapp.com/guardarropas_id/prendas/sugerencias'
200 OK
204 No Content
body {[
  {
  	"id": 1,
    "prendasSuperiores": "1",
    "prendasInferiores": "2", 
    "calzados": "4", 
  },
	....
]}
