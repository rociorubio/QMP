class Usuario {
	ProveedoresDeClima proveedor;
	TipoUsuario tipoUsuario;

	public Usuario(TipoUsuario tipoUsuario){
		this.tipoUsuario = tipoUsuario
		this.proveedor = new ProveedoresDeClima().getProveedorDefault()
	}


	public getClimaBsAs(Date hora){
		proveedor.getClima("Buenos Aires, Argentina", hora);
	}

	public getSugerencia(){	
		
		prendaSuperior = this.obtenerPrendaCondicion(Categoria.SUPERIOR);
		prendaInferior = this.obtenerPrendaCondicion(Categoria.INFERIOR);
		prendaCalzado = this.obtenerPrendaCondicion(Categoria.CALZADO);
		prendaAccesorio = this.obtenerPrendaCondicion(Categoria.ACCESORIOS);
		// agarrar una prenda superior, inferior, calzado y accesorio, y verificar si la temperatura actual esta entre la indicada para esa prenda
		return [prendaSuperior, prendaInferior, prendaCalzado, prendaAccesorio];
	}

	public obtenerPrendaCondicion(Categoria categoria){
		listaDePrendas = GUARDARROPA.getPrendasValidas();
		return listaDePrendas.filter (prenda -> prenda.getCategoria() == categoria && 
			this.getClimaBsAs > prenda.getTemperaturaMinima() && this.getClimaBsAs < prenda.getTemperaturaMaxima()).take(1);

	}

	public ProveedoresDeClima setProveedor(ProveedoresDeClima nuevoProveedor) {
		if(tipoUsuario = TipoUsuario.USUARIOADMIN){
			proveedor.setProveedor(nuevoProveedor)
		}
	}

}

enum TipoUsuario{

	USUARIONORMAL;
	USUARIOADMIN;
}

class ProveedoresDeClima {
	ProveedoresDeClima proveedor;

	public ProveedoresDeClima(){
		this.proveedor = new AccuWheather();
	}
	
	public Integer getClima(String ciudad, Date hora){}
	public ProveedoresDeClima getProveedorDefault(){ return this.proveedor; }
	public ProveedoresDeClima setProveedor(ProveedoresDeClima nuevoProveedor){ this.proveedor = nuevoProveedor; }
}

class AccuWheather extends ProveedoresDeClima{

	public Integer getClima(String ciudad, Date hora) {
		AccuWeatherAPI apiClima =  new AccuWeatherAPI();
		List<Map<String,Object>> condicionesClimaticas = apiClima.getWeather("Buenos Aires, Argentina");
		condicionesClimaticas.get(hora).get("Temperature");
	}
}

class Prenda{
	TipoPrenda tipo;
	Material material;
	Color colorPrimario;
	Color colorSecundario;
	Trama trama;
	Clima estacion;


	public Prenda(Tipo tipo){
	  if(tipo == null) {
	  	throw new PrendaInvalidaException(
	    		"Faltan datos requeridos"
	    	)
	  }
	  this.tipo = tipo;
	}

	public setMaterial(Color colorPrimario, Color colorSecundario, Material material, Trama trama, Clima estacion){
		if(material == null || colorPrimario == null || estacion == null) {
	   		throw new PrendaInvalidaException(
	    		"Faltan datos requeridos"
	    	)
	  	}
	  	if(trama == null){
	  		this.trama = Trama.LISA;
	  	}
	  	else {
	  		this.trama = trama;
	  	}
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
		this.material = material;
		this.estacion = estacion;
	}	

	public verBorrador(){
		return this
	}
		
	public Categoria getCategoria(){
		if(tipo == null) {
		    throw new PrendaInvalidaException(
		      "Faltan datos requeridos"
		    )
		}

	    return tipo.getCategoria()
	 }


	public guardarPrenda(){
        if(esPrendaValida()){
            GUARDARROPA.agregarPrenda(this);
        }
    }

    public esPrendaValida(){
        return this.material != null && this.colorPrimario != null
    }

    public Integer getTemperaturaMinima() { return estacion.getTemperaturaMinima(); }
    public Integer getTemperaturaMaxima() { return estacion.getTemperaturaMaxima(); }
}

Object GUARDARROPA{
    List<Prenda> prendasValidas = new ArrayList<>();

    public void agregarPrenda(Prenda prenda){
        prendasValidas.add(prenda);
    }

    public List<Prenda> getPrendasValidas(){
    	return prendasValidas;
    }
}


public PrendaInvalidaException extends RuntimeException {
  public PrendaInvalidaException(String causa){
    super(causa)
  }
}

class Uniforme{
	Colegio colegio;
	Prenda parteSuperior;
	Prenda parteInferior;
	Prenda calzado;
	List<String> sugerencias;

	public Uniforme(Colegio colegio, Prenda parteSuperior, Prenda parteInferior, Prenda calzado){
		if(parteSuperior.getCategoria() =! SUPERIOR || 
			parteInferior.getCategoria() =! INFERIOR || 
			calzado.getCategoria() =! CALZADO){
				throw new PrendaInvalidaException(
		      	"La prenda no corresponde a su tipo"
		    	)
		}

		this.colegio = colegio;
		this.parteSuperior = parteSuperior;
		this.parteInferior = parteInferior;
		this.calzado = calzado;

	}

	public Sugerencias(String sugerencia){
		sugerencias.add(sugerencia)


	}


}

enum Colegio{
	SANJUAN,
	JOHNSON
}


enum TipoPrenda(categoria){
	Categoria categoria;

	public getCategoria(){ return this.categoria }

	CAMISA(Categoria.SUPERIOR),
	CHOMBA(Categoria.SUPERIOR),
	CAMPERA(Categoria.SUPERIOR),
	PANTALON(Categoria.INFERIOR),
	ZAPATO(Categoria.CALZADO),
	ANTEOJOS(Categoria.ACCESORIOS),
	PAÑUELO(Categoria.ACCESORIOS)
}

enum Categoria{
  SUPERIOR, INFERIOR, CALZADO, ACCESORIOS
}

class Color{
	Int red;
	Int green;
	Int blue;
}

enum Material{

	CUERO, 
	JEAN, 
	ALGODON,
	PIQUE,
	ACETATO
}

enum Trama{
	LISA, RAYADA, LUNARES, CUADRILLE, ESTAMPADA
}

class Clima{
	Integer temperaturaMinima;
	Integer temperaturaMaxima;

	public Integer getTemperaturaMinima(){ return temperaturaMinima; }
	public Integer getTemperaturaMaxima(){ return temperaturaMaxima; }
}