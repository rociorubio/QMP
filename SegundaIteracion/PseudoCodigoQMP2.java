class Prenda{
	TipoPrenda tipo;
	Material material;
	Color colorPrimario;
	Color colorSecundario;
	Trama trama;


	public Prenda(Tipo tipo){
	  if(tipo == null) {
	  	throw new PrendaInvalidaException(
	    		"Faltan datos requeridos"
	    	)
	  }
	  this.tipo = tipo;
	}

	public setMaterial(Color colorPrimario, Color colorSecundario, Material material, Trama trama){
		if(material == null || colorPrimario == null) {
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
}

Object GUARDARROPA{
    List<Prenda> prendasValidas = new ArrayList<>();

    public void agregarPrenda(Prenda prenda){
        prendasValidas.add(prenda);
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