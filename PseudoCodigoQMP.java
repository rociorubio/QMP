class Prenda {
	TipoPrenda tipo;
	Material material;
	Color colorPrimario;
	Color colorSecundario;

	public Prenda(tipo, material, colorPrimario, colorSecundario){
	  if(tipo == null || material == null || colorPrimario == null) {
	    throw new PrendaInvalidaException(
	      "Faltan datos requeridos"
	    )
	  }
	  this.tipo = tipo;
	  this.material = material;
	  this.colorPrimario = colorPrimario;
	  this.colorSecundario = colorSecundario;
	}
	
	public Categoria getCategoria(){
	    return tipo.getCategoria()
	  }
}

public PrendaInvalidaException extends RuntimeException {
  public PrendaInvalidaException(String causa){
    super(causa)
  }
}

enum TipoPrenda(categoria){
	Categoria categoria;

	public getCategoria(){ return this.categoria }

	CAMISA(Categoria.SUPERIOR),
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
	CUERO, JEAN, ALGODON
}