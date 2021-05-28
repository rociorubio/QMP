class Prenda{
	TipoPrenda tipoPrenda;
	Material material;
	Color colorPrimario;
	Color colorSecundario;
	Trama trama;
	double temperaturaAdecuada;
	CriterioDeRopa criterioDeRopa;

	public Prenda(TipoPrenda tipoPrenda, Material material, Color colorPrimario, Color colorSecundario,Trama trama, double temperaturaAdecuada){
		this.tipoPrenda = tipoPrenda;
		this.material = material;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
		this.temperaturaAdecuada = temperaturaAdecuada;
	}

	public getCategoria(){return tipoPrenda.getCategoria();}
	public esTemperaturaAcorde(CondicionesClimaticas condicionesClimaticas){
		return getTemperaturaAdecuada() > condicionesClimaticas.getTemperaturaMinima() 
				&& getTemperaturaAdecuada() < condicionesClimaticas.getTemperaturaMaxima();
	}
}

class Borrador{
	TipoPrenda tipoPrenda;
	Material material;
	Color colorPrimario;
	Color colorSecundario;
	Trama trama = Trama.LISA;
	double temperaturaAdecuada;

	public void Borrador(tipoDePrenda){
       validateNonNull(tipoDePrenda)
       this.tipoDePrenda = tipoDePrenda
	}

	public void especificarMaterial(material){
       validateNonNull(material) 
       this.validarMaterialConsistenteConTipoDePrenda(material) 
       this.material = material
	}

    public void especificarColorPrincipal(color){
       validateNonNull(color) 
       this.colorPrincipal = color
   }

    public void especificarTrama(trama){
        if(trama == null){
        	this.trama = Trama.LISA;
        }else{
        	this.trama = trama;
		}
	}
	public Prenda crearPrenda(){
		if(esPrendasValida()){
			return new Prenda(tipoPrenda, material, colorPrimario, colorSecundario, tramaMaterial)
		}
	}

	public esPrendaVálida(){ return this.material != null && this.colorPrimario != null && this.trama != null}

	public void setMaterial(Material material){this.material = material}
	public void setColorPrimario(Color colorPrimario){this.colorPrimario = colorPrimario}
	public void setColorSecundario(Color colorSecundario){this.colorSecundario = colorSecundario}
	public void setTrama(Trama trama){this.trama = trama}
	public void setTemperaturaAdecuada(double temperaturaAdecuada){this.temperaturaAdecuada = temperaturaAdecuada}
}

public class Usuario{
	List<Guardarropa> guardarropas;
	List<Guardarropa> guardarropasCompartidos;

	public List<SugerenciasDeUsuarios> listarPropuestasDeModificación(){
		guardarropas.forEach(guardarropas -> guardarropas.getSugerencias());
	}
}

public class GuardarropaBuilder{
    List<Prenda> prendasSuperiores;
    List<Prenda> prendasInferiores;
    List<Prenda> calzados;
    List<Prenda> accesorios;
    CriterioDeRopa criterio;

    public GuardarropaBuilder(CriterioDeRopa criterioDeRopa){
    	this.criterioDeRopa = criterioDeRopa;
    }

	public agregarPrendasSuperiores(List<Prenda>prendas){
		validarPrenda(prenda, SUPERIOR);
		prendasSuperiores.add(prendas);
    }

	public agregarPrendasInferiores(List<Prenda>prendas){
		validarPrenda(prenda, INFERIOR);
		prendasInferiores.add(prendas);
    }

	public agregarCalzados(List<Prenda>prendas){
		validarPrenda(prenda, CALZADO);
		calzados.add(prendas);
    }

	public agregarAccesorios(List<Prenda>prendas){
		validarPrenda(prenda, ACCESORIOS);
		accesorios.add(prendas);
	}

	public verificarPrenda(Prenda prenda, Categoria categoria) {
    	if(prenda.getCriterioDeRopa() =! this.categoriaDeRopa || prenda.getCategoria() =! categoria)
    		throw new PrendaInvalidaDeGuardarropaException("No se puede agregar la prenda al guardarropas")
	}

    public generarGuardarropa(){
    	return new Guardarropas(prendasSuperiores, prendasInferiores, calzados, accesorios, criterio);
    }
}


public class Guardarropas{
    List<Prenda> prendasSuperiores;
    List<Prenda> prendasInferiores;
    List<Prenda> calzados;
    List<Prenda> accesorios;
    List<SugerenciasDeUsuarios> sugerenciasDeUsuarios;
    CriterioDeRopa criterio;


    public Guardarropas(prendasSuperiores, prendasInferiores, calzados, accesorios, criterio){
    	this.prendasSuperiores = prendasSuperiores
    	this.prendasInferiores = prendasInferiores
    	this.calzados = calzados
    	this.accesorios = accesorios
    	this.criterio = criterio
    }

   	public boolean existe(Prenda prenda){
   		if(prendasSuperiores.contains(prenda) || prendasInferiores.contains(prenda) || calzado.contains(prenda) || accesorios.contains(prenda))
   			throw new PrendaExisteEnGuardarropaException("Prenda ya existente")
   	}

    public resolverSugerencia(SugerenciasDeUsuarios sugerencia){ sugerencia.ejecutar(this); }
    public deshacerSugerencia(Sugerencia){ sugerencia.deshacer(this); }
   	public List<SugerenciasDeUsuarios> getSugerencias(){ return sugerenciasDeUsuarios; }
	public List <SugerenciasDeUsuarios> setSugerencia(SugerenciasDeUsuarios sugerencia) {
    	sugerenciasDeUsuarios.add(sugerencia);
	}
}

public enum CriterioDeRopa{
	ROPA_DE_VIAJE,
	ROPA_DE_ENTRECASA,
}


public interface SugerenciasDeUsuarios{
	public ejecutar(Guardarropa guardarropa);
	public deshacer(Guardarropa guardarropa);
}

public class AgregarPrendaDeSugerencia implements SugerenciasDeUsuarios{
	Prenda prenda;
	int cantidadDeVecesSugeridas;
	boolean ejecutado = false;

	public AgregarPrendaDeSugerencia(Prenda prenda){
		this.prenda = prenda;
		this.cantidadDeVecesSugeridas = 0;
	}

	public ejecutar(Guardarropa guardarropa){
		if(guardarropa.existe(this.prenda)
		throw new PrendaYaExisteEnGuardarropaException("Prenda ya existe en guardarropa.");
		guardarropa.cumpleCriterio(Prenda prenda);
		guardarropa.setSugerencia(this.prenda);
		ejecutado = true;	//horrible
		cantidadDeVecesSugeridas++;
	}

	public deshacer(Guardarropa guardarropa){
		if(ejecutado){
			guardarropa.remove(prenda);
		} else {
			throw new NoSeEjecutoAgregarPrendaDeSugerenciaException("La prenda necesita estar presente en el guardarropa para eliminarla");
		}
	}
}

public class QuitarPrendaDeSugerencia implements SugerenciasDeUsuarios{
	Prenda prenda;
	int cantidadDeVecesSugeridas;
	boolean ejecutado = false;

	public QuitarPrendaDeSugerencia(Prenda prenda){
		this.prenda = prenda;
		this.cantidadDeVecesSugeridas = 0;
	}

	public ejecutar(Guardarropa guardarropa){
		if (guardarropa.existe(prenda)){
			guardarropa.remove(this.prenda);
		} else {
			PrendaNoExisteEnGuardarropaException("Prenda no existe en guardarropa.");
		}
		ejecutado = true;
		cantidadDeVecesSugeridas++;
	}

	public deshacer(Guardarropa guardarropa){
		if(ejecutado)
			guardarropa.setSugerencia(this.prenda);
	}
}
