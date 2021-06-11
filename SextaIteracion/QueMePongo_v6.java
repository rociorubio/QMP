class Prenda{
	TipoPrenda tipoPrenda;
	Material material;
	Color colorPrimario;
	Color colorSecundario;
	Trama trama;
	double temperaturaAdecuada;

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

enum TipoPrenda{
	Categoria categoria;
	public TipoPrenda(Categoria categoria);

	public categoria(){
		return this.categoria;
	}

	ZAPATOS(Categoria.CALZADO),
	CAMISADEMANGASCORTAS(Categoria.PARTE_SUPERIOR),
	PANTALON(Categoria.PARTE_INFERIOR)
}

enum Categoria{
	PARTE_SUPERIOR,
	CALZADO,
	PARTE_INFERIOR,
	ACCESORIOS
}



public class Usuario{
	List<Guardarropa> guardarropas;
	String mail;

	public List<SugerenciasDeUsuarios> listarPropuestasDeModificación(){
		guardarropas.forEach(guardarropas -> guardarropas.getSugerencias());
	}

	public String getMail(){return mail;}
}

public class Guardarropas{
    List<Prenda> prendasSuperiores;
    List<Prenda> prendasInferiores;
    List<Prenda> calzados;
    List<Prenda> accesorios;
    List<SugerenciasDeUsuarios> sugerenciasDeUsuarios;

    public Guardarropas(prendasSuperiores, prendasInferiores, calzados, accesorios, criterio){
    	this.prendasSuperiores = prendasSuperiores
    	this.prendasInferiores = prendasInferiores
    	this.calzados = calzados
    	this.accesorios = accesorios
    	this.criterio = criterio
    }


   	public agregarSugerencia(SugerenciasDeUsuarios sugerencia){
   		sugerenciasDeUsuarios.add(Sugerencia);
   	}

    public resolverSugerencia(SugerenciasDeUsuarios sugerencia){ sugerencia.ejecutar(this); }
    public deshacerSugerencia(Sugerencia sugerencia){ sugerencia.deshacer(this); }
   	public List<SugerenciasDeUsuarios> getSugerenciasDeUsuarios(){ return sugerenciasDeUsuarios; }
}

public interface SugerenciasDeUsuarios{
	public ejecutar(Guardarropa guardarropa);
	public deshacer(Guardarropa guardarropa);
}

public class AgregarPrendaDeSugerencia implements SugerenciasDeUsuarios{
	Prenda prenda;
	boolean ejecutado = false;

	public AgregarPrendaDeSugerencia(Prenda prenda){
		this.prenda = prenda;
	}

	public ejecutar(Guardarropa guardarropa){
		if(guardarropa.existe(this.prenda)
			throw new PrendaYaExisteEnGuardarropaException("Prenda ya existe en guardarropa.");
		guardarropa.cumpleCriterio(Prenda prenda);
		guardarropa.agregarPrenda(this.prenda);
		ejecutado = true;	//horrible
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
	boolean ejecutado = false;

	public QuitarPrendaDeSugerencia(Prenda prenda){
		this.prenda = prenda;
	}

	public ejecutar(Guardarropa guardarropa){
		if (guardarropa.existe(prenda)){  
			guardarropa.remove(this.prenda);
		} else {
			PrendaNoExisteEnGuardarropaException("Prenda no existe en guardarropa.");
		}
		ejecutado = true;	//horrible
		cantidadDeVecesSugeridas++;
	}

	public deshacer(Guardarropa guardarropa){
		if(ejecutado)
			guardarropa.add(this.prenda);	
	}
}

// ############################################################################
public interface GeneradorDeSugerencias{
	List<Prenda> prendasSuperiores;
    List<Prenda> prendasInferiores;
    List<Prenda> calzados;
    
	List<Atuendo> generarSugerencias(List<Prenda> prendasAptas);
}

public class GeneradorDeSugerenciaPosta implements GeneradorDeSugerencias{
	List<Atuendo> generarSugerencias(List<Prenda> prendasAptas){}	
}

//
public class AsesorDeImagen{
	GeneradorDeSugerencias generadorDeSugerencias;
	ServicioMeteorologico servicioMeteorologico;
	Usuario usuario;
	List<EstadoDelTiempo> notificadores;

	public Atuendo sugerirAtuendo(String direccion, Guardarropas guardarrpas) {

		EstadoDelTiempo estadoDelTiempo = servicioMeteorologico.obtenerCondicionesClimaticas(direccion);

		List<Atuendo> atuendoCombinacion = generadorDeSugerencias.todasLasPosiblesCombinaciones(guardarropa.getTodasLasPrendas())

		if(estadoDelTiempo.getAlert() =! nill){
			String alert = estadoDelTiempo.getAlert();
			if(alert == 'Tormenta'){
				notificadoresDeAtuendo.clone().forEach{notificador -> notificador.notificar(usuario, "Se recomienda llevar paraguas, alerta de tormentas")}
			}
			if(alert == 'Granizo'){
				notificadoresDeAtuendo.clone().forEach{notificador -> notificador.notificar(usuario, "Se recomienda no sacar el auto, alerta de granizo")}
			}
		}

		return atuendoCombinacion
			.filter(atuendo -> atuendo.aptaParaTemperatura(estadoDelTiempo.temperatura))
			.filter(atuendo -> atuendo.aptaParaHumedad(estadoDelTiempo.humedad))
			.first();
	}
}

public interface ServicioMeteorologico {
   EstadoDelTiempo obtenerCondicionesClimaticas(String direccion);
}

public class ServicioMeteorologicoAccuWeather {
	private EstadoDelTiempo ultimasRespuestas;
	private List<EstadoDelTiempo> listaDeEstadosDelTiempo;
  	private LocalDateTime proximaExpiracion;
  	private AsesorDeImagen notificadorDeAtuendo;
  
	public ServicioMeteorologico(AccuWeatherAPI api, Duration periodoDeValidez, String direccion) { 
		this.api = api;                
		this.expiracion = expiracion;  
		this.direccion = direccion;
	}

	public EstadoDelTiempo obtenerCondicionesClimaticas(String direccion) {
		if (this.ultimaRespuesta == null || this.expiro()) {
			this.ultimaRespuesta = consultarApi(direccion);
			this.proximaExpiracion = LocalDateTime.now().plus(this.proximaExpiracion); 
			listaDeEstadosDelTiempo.add(this.ultimaRespuesta);
		}

		if(this.ultimaRespuesta.getAlert() != nill && this.ultimaRespuesta.getAlert() =! listaDeEstadosDelTiempo.last() ) {
	    	notificadorDeAtuendo.clone().forEach{notificador -> notificador.sugerirAtuendo()}
		}
		return this.ultimaRespuesta;
	}  

	private boolean expiro() { return !proximaExpiracion.isAfter(DateTime.now); }
	
	private EstadoDelTiempo consultarApi(String direccion) {
	    EstadoDelTiempo respuesta = this.api.getWeather(direccion).get(0);
	    String alerta = this.api.getAlert(direccion).get(0);
	    int temperatura = (int) respuesta.get(...); 
	    Humedad humedad = (double) respuesta.get(...) > 0.8 ? LLUVIOSO : SECO;

	    return new EstadoDelTiempo(temperatura, humedad, alerta);
  }
}


//Reminder: Este es nuestra sugerencia.
public class Atuendo{
	Prenda parteSuperior;
	Prenda parteInferior;
	Prenda calzado;
	Prenda accesorio;

	public boolean aptaParaTemperatura(CondicionesClimaticas temp){
		return parteSuperior.esTemperaturaAcorde(temp)
			&& parteInferior.esTemperaturaAcorde(temp)
			&& calzado.esTemperaturaAcorde(temp)
			&& accesorios.esTemperaturaAcorde(temp)
	}
}

//Sexta Iteracion

// Punto 5: Actualizar el codigo de ServicioMeteorologicoAccuWeather para que cuando se actualice un EstadoDelTiempo, se genere un nuevo atuendo (sugerencia)

public class EstadoDelTiempo{
	DateTime fecha;
	int temperatura;
	String alertaMeteorológica;
	Humedad humedad;

	public EstadoDelTiempo(int temp, Humedad hum, String alert){
		this.fecha = Datetime.now()
		this.temperatura = temp;
		this.hum = humedad;
		this.notificadores = new arrayList<>();
	}

	public void notificar(){
		notificadores.clone().forEach(notificador -> notificador.notificar());
	}

	public void agregarNotificador(Notificador notificador){
		notificadores.add(notificador);
	}

	public void eliminarNotificador(Notificador notificador){
		notificadores.remove(notificador);
	}
}

public interface Notificador{
	public void notificar(Usuario usuario, String mensaje);
}

public class MailSender{
	public void notificar(Usuario usuario, String mensaje){
		send(usuario.getMail(), mensaje);
	}
}

public class NotificationService{
	public void notificar(Usuario usuario, String mensaje){
		notify(mensaje);
	}
}