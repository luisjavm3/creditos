package com.empresa.creditos;

import java.sql.Date;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import com.empresa.creditos.entity.Cliente;
import com.empresa.creditos.entity.Cobrador;
import com.empresa.creditos.entity.Cobro;
import com.empresa.creditos.entity.credito.Credito;
import com.empresa.creditos.entity.credito.Interes;
import com.empresa.creditos.entity.credito.Perioricidad;
import com.empresa.creditos.entity.credito.Plazo;
import com.empresa.creditos.entity.direccion.Departamento;
import com.empresa.creditos.entity.direccion.Direccion;
import com.empresa.creditos.entity.direccion.Municipio;
import com.empresa.creditos.entity.liquidacion.Abono;
import com.empresa.creditos.entity.liquidacion.Liquidacion;
import com.empresa.creditos.entity.telefono.TelefonoCliente;
import com.empresa.creditos.service.IClienteService;
import com.empresa.creditos.service.ICobradorService;
import com.empresa.creditos.service.ICobroService;
import com.empresa.creditos.service.credito.ICreditoService;
import com.empresa.creditos.service.credito.IInteresService;
import com.empresa.creditos.service.credito.IPerioricidadService;
import com.empresa.creditos.service.credito.IPlazoService;
import com.empresa.creditos.service.direccion.IDepartamentoService;
import com.empresa.creditos.service.direccion.IDireccionService;
import com.empresa.creditos.service.direccion.IMunicipioService;
import com.empresa.creditos.service.liquidacion.ILiquidacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestData {

	@Autowired
	private IInteresService interesService;
	@Autowired
	private IPerioricidadService perioricidadService;
	@Autowired
	private IPlazoService plazoService;
	@Autowired
	private IDireccionService direccionService;
	@Autowired
	private IDepartamentoService departamentoService;
	@Autowired
	private IMunicipioService municipioService;
	@Autowired
	private ICobroService cobroService;
	@Autowired
	private ICobradorService cobradorService;
	@Autowired
	private ICreditoService creditoService;
	@Autowired
	private ILiquidacionService liquidacionService;
	@Autowired
	private IClienteService clienteService;

	private Departamento cordoba;
	private Municipio monteria;
	private Cobro cobro;

	@PostConstruct
	public void populateDatabase() {

		this.insertarTodosLosDepartamentosDeColombia();
		cordoba = departamentoService.findByDepartamento("CORDOBA");
		this.insertarMunicipiosDeCordoba();
		monteria = municipioService.findByMunicipio("MONTERIA");
		this.insertarInteresPorDefecto();
		this.insertarPlazosPorDefecto();
		this.insertarPerioricidadPorDefecto();

		// COBROS Y COBRADORES
		Cobro cobro1 = new Cobro("PRIMER COBRO ( 1 )");
		Cobro cobro2 = new Cobro("SEGUNDO COBRO ( 2 )");

		Cobrador cobrador1 = new Cobrador(123457, "NOMBRES COBRADOR UNO", "APELLIDOS COBRADOR UNO", "EL ZARCO");
		Direccion direccionCobrador1 = new Direccion(cordoba, monteria, null,
				"AVENIDA SIEMPREVIVA, CALLE DEL INFIERNO");
		cobrador1.setDireccion(direccionCobrador1);

		cobro1.setCobrador(cobrador1);
		this.cobro = cobroService.save(cobro1); // Asignado el valor del cobro de prueba como atributo de la clase
		cobroService.save(cobro2);

		// // CLIENTES
		insertarClientesDePrueba();

		// // CREDITOS
		insertarCreditosDePrueba();

		// LIQUIDACIONES
		insertarLiquidacionesDePrueba();
		realizarLiquidacionDeLaPrimeraLiquidacion();
		realizarSegundaLiquidacionDeLaPrimeraLiquidacion();

	}

	public void insertarTodosLosDepartamentosDeColombia() {

		Departamento d1 = new Departamento();
		d1.setDepartamento("AMAZONAS");
		Departamento d2 = new Departamento();
		d2.setDepartamento("ANTIOQUIA");
		Departamento d3 = new Departamento();
		d3.setDepartamento("ARAUCA");
		Departamento d4 = new Departamento();
		d4.setDepartamento("ATLANTICO");
		Departamento d5 = new Departamento();
		d5.setDepartamento("BOLIVAR");
		Departamento d6 = new Departamento();
		d6.setDepartamento("BOYACA");
		Departamento d7 = new Departamento();
		d7.setDepartamento("CALDAS");
		Departamento d8 = new Departamento();
		d8.setDepartamento("CAQUETA");
		Departamento d9 = new Departamento();
		d9.setDepartamento("CASANARE");
		Departamento d10 = new Departamento();
		d10.setDepartamento("CAUCA");
		Departamento d11 = new Departamento();
		d11.setDepartamento("CESAR");
		Departamento d12 = new Departamento();
		d12.setDepartamento("CHOCO");
		Departamento d13 = new Departamento();
		d13.setDepartamento("CORDOBA");
		Departamento d14 = new Departamento();
		d14.setDepartamento("CUNDINAMARCA");
		Departamento d15 = new Departamento();
		d15.setDepartamento("GUAINIA");
		Departamento d16 = new Departamento();
		d16.setDepartamento("GUAVIARE");
		Departamento d17 = new Departamento();
		d17.setDepartamento("HUILA");
		Departamento d18 = new Departamento();
		d18.setDepartamento("LA GUAJIRA");
		Departamento d19 = new Departamento();
		d19.setDepartamento("MAGDALENA");
		Departamento d20 = new Departamento();
		d20.setDepartamento("META");
		Departamento d21 = new Departamento();
		d21.setDepartamento("NARIÑO");
		Departamento d22 = new Departamento();
		d22.setDepartamento("NORTE DE SANTANDER");
		Departamento d23 = new Departamento();
		d23.setDepartamento("PUTUMAYO");
		Departamento d24 = new Departamento();
		d24.setDepartamento("QUINDIO");
		Departamento d25 = new Departamento();
		d25.setDepartamento("RISARALDA");
		Departamento d26 = new Departamento();
		d26.setDepartamento("SAN ANDRES Y PROVIDENCIA");
		Departamento d27 = new Departamento();
		d27.setDepartamento("SANTANDER");
		Departamento d28 = new Departamento();
		d28.setDepartamento("SUCRE");
		Departamento d29 = new Departamento();
		d29.setDepartamento("TOLIMA");
		Departamento d30 = new Departamento();
		d30.setDepartamento("VALLE DEL CAUCA");
		Departamento d31 = new Departamento();
		d31.setDepartamento("VAUPES");
		Departamento d32 = new Departamento();
		d32.setDepartamento("VICHADA");
		this.departamentoService.save(d1);
		this.departamentoService.save(d2);
		this.departamentoService.save(d3);
		this.departamentoService.save(d4);
		this.departamentoService.save(d5);
		this.departamentoService.save(d6);
		this.departamentoService.save(d7);
		this.departamentoService.save(d8);
		this.departamentoService.save(d9);
		this.departamentoService.save(d10);
		this.departamentoService.save(d11);
		this.departamentoService.save(d12);
		this.departamentoService.save(d13);
		this.departamentoService.save(d14);
		this.departamentoService.save(d15);
		this.departamentoService.save(d16);
		this.departamentoService.save(d17);
		this.departamentoService.save(d18);
		this.departamentoService.save(d19);
		this.departamentoService.save(d20);
		this.departamentoService.save(d21);
		this.departamentoService.save(d22);
		this.departamentoService.save(d23);
		this.departamentoService.save(d24);
		this.departamentoService.save(d25);
		this.departamentoService.save(d26);
		this.departamentoService.save(d27);
		this.departamentoService.save(d28);
		this.departamentoService.save(d29);
		this.departamentoService.save(d30);
		this.departamentoService.save(d31);
		this.departamentoService.save(d32);
	}

	public void insertarMunicipiosDeCordoba() {

		cordoba.setMunicipios(new ArrayList<>());

		Municipio m1 = new Municipio("AYAPEL");
		Municipio m2 = new Municipio("BUENAVISTA");
		Municipio m3 = new Municipio("CANALETE");
		Municipio m4 = new Municipio("CERETE");
		Municipio m5 = new Municipio("CHIMA");
		Municipio m6 = new Municipio("CHINU");
		Municipio m7 = new Municipio("CIENAGA DE ORO");
		Municipio m8 = new Municipio("COTORRA");
		Municipio m9 = new Municipio("LA APARTADA");
		Municipio m10 = new Municipio("LOS CORDOBAS");
		Municipio m11 = new Municipio("MOMIL");
		Municipio m12 = new Municipio("MONTELIBANO");
		Municipio m13 = new Municipio("MONTERIA");
		Municipio m14 = new Municipio("MOÑITOS");
		Municipio m15 = new Municipio("PLANETA RICA");
		Municipio m16 = new Municipio("PUEBLO NUEVO");
		Municipio m17 = new Municipio("PUERTO ESCONDIDO");
		Municipio m18 = new Municipio("PUERTO LIBERTADOR");
		Municipio m19 = new Municipio("PURISIMA");
		Municipio m20 = new Municipio("SAHAGUN");
		Municipio m21 = new Municipio("SAN ANDRES DE SOTAVENTO");
		Municipio m22 = new Municipio("SAN ANTERO");
		Municipio m23 = new Municipio("SAN BERNARDO DEL VIENTO");
		Municipio m24 = new Municipio("SAN CARLOS");
		Municipio m25 = new Municipio("SAN JOSE DE URE");
		Municipio m26 = new Municipio("SAN PELAYO");
		Municipio m27 = new Municipio("SANTA CRUZ DE LORICA");
		Municipio m28 = new Municipio("TIERRALTA");
		Municipio m29 = new Municipio("TUCHIN");
		Municipio m30 = new Municipio("VALENCIA");
		cordoba.addMunicipio(m1);
		cordoba.addMunicipio(m2);
		cordoba.addMunicipio(m3);
		cordoba.addMunicipio(m4);
		cordoba.addMunicipio(m5);
		cordoba.addMunicipio(m6);
		cordoba.addMunicipio(m7);
		cordoba.addMunicipio(m8);
		cordoba.addMunicipio(m9);
		cordoba.addMunicipio(m10);
		cordoba.addMunicipio(m11);
		cordoba.addMunicipio(m12);
		cordoba.addMunicipio(m13);
		cordoba.addMunicipio(m14);
		cordoba.addMunicipio(m15);
		cordoba.addMunicipio(m16);
		cordoba.addMunicipio(m17);
		cordoba.addMunicipio(m18);
		cordoba.addMunicipio(m19);
		cordoba.addMunicipio(m20);
		cordoba.addMunicipio(m21);
		cordoba.addMunicipio(m22);
		cordoba.addMunicipio(m23);
		cordoba.addMunicipio(m24);
		cordoba.addMunicipio(m25);
		cordoba.addMunicipio(m26);
		cordoba.addMunicipio(m27);
		cordoba.addMunicipio(m28);
		cordoba.addMunicipio(m29);
		cordoba.addMunicipio(m30);
		departamentoService.save(cordoba);
	}

	public void insertarInteresPorDefecto() {

		Interes interes1 = new Interes(10);
		Interes interes2 = new Interes(20);
		Interes interes3 = new Interes(30);
		Interes interes4 = new Interes(40);
		Interes interes5 = new Interes(50);
		Interes interes6 = new Interes(60);
		Interes interes7 = new Interes(70);
		Interes interes8 = new Interes(80);
		Interes interes9 = new Interes(90);
		Interes interes10 = new Interes(100);

		this.interesService.save(interes1);
		this.interesService.save(interes2);
		this.interesService.save(interes3);
		this.interesService.save(interes4);
		this.interesService.save(interes5);
		this.interesService.save(interes6);
		this.interesService.save(interes7);
		this.interesService.save(interes8);
		this.interesService.save(interes9);
		this.interesService.save(interes10);
	}

	public void insertarPlazosPorDefecto() {

		Plazo plazo1 = new Plazo(1, "DIARIO");
		Plazo plazo2 = new Plazo(7, "SEMANAL");
		Plazo plazo3 = new Plazo(15, "QUINCENAL");
		Plazo plazo4 = new Plazo(30, "MENSUAL");
		Plazo plazo5 = new Plazo(60, "BI-MENSUAL");
		Plazo plazo6 = new Plazo(90, "TRI-MESTRAL");
		Plazo plazo7 = new Plazo(180, "SEMESTRAL");
		Plazo plazo8 = new Plazo(360, "ANUAL");

		this.plazoService.save(plazo1);
		this.plazoService.save(plazo2);
		this.plazoService.save(plazo3);
		this.plazoService.save(plazo4);
		this.plazoService.save(plazo5);
		this.plazoService.save(plazo6);
		this.plazoService.save(plazo7);
		this.plazoService.save(plazo8);
	}

	public void insertarPerioricidadPorDefecto() {

		Perioricidad perioricidad1 = new Perioricidad(1, "DIARIO");
		Perioricidad perioricidad2 = new Perioricidad(7, "SEMANAL");
		Perioricidad perioricidad3 = new Perioricidad(30, "MENSUAL");

		this.perioricidadService.save(perioricidad1);
		this.perioricidadService.save(perioricidad2);
		this.perioricidadService.save(perioricidad3);
	}

	public void insertarClientesDePrueba() {

		Cobro cobro;

		try {
			cobro = cobroService.findById(1);
		} catch (Exception e) {
			System.err.println("============== ERROR ==============");
			System.out.println("TestData.insertarClientesDePrueba()");
			return;
		}

		cobro.setClientes(new ArrayList<>());

		Direccion direccion1 = new Direccion(cordoba, monteria, null, "DIRECCION DEL PRIMER CLIENTE");
		Direccion direccion2 = new Direccion(cordoba, monteria, null, "DIRECCION DEL SEGUNDO CLIENTE");
		Direccion direccion3 = new Direccion(cordoba, monteria, null, "DIRECCION DEL TERCER CLIENTE");
		Direccion direccion4 = new Direccion(cordoba, monteria, null, "DIRECCION DEL CUARTO CLIENTE");
		Direccion direccion5 = new Direccion(cordoba, monteria, null, "DIRECCION DEL QUINTO CLIENTE");
		Direccion direccion6 = new Direccion(cordoba, monteria, null, "DIRECCION DEL SEXTO CLIENTE");
		Direccion direccion7 = new Direccion(cordoba, monteria, null, "DIRECCION DEL SEPTIMO CLIENTE");
		Direccion direccion8 = new Direccion(cordoba, monteria, null, "DIRECCION DEL OCTAVO CLIENTE");
		Direccion direccion9 = new Direccion(cordoba, monteria, null, "DIRECCION DEL NOVENO CLIENTE");
		Direccion direccion10 = new Direccion(cordoba, monteria, null, "DIRECCION DEL DECIMO CLIENTE");
		direccion1 = this.direccionService.save(direccion1);
		direccion2 = this.direccionService.save(direccion2);
		direccion3 = this.direccionService.save(direccion3);
		direccion4 = this.direccionService.save(direccion4);
		direccion5 = this.direccionService.save(direccion5);
		direccion6 = this.direccionService.save(direccion6);
		direccion7 = this.direccionService.save(direccion7);
		direccion8 = this.direccionService.save(direccion8);
		direccion9 = this.direccionService.save(direccion9);
		direccion10 = this.direccionService.save(direccion10);

		Cliente cliente1 = new Cliente("1123456", "NOMBRES CLIENTE UNO", "APELLIDOS CLIENTE UNO", "PRIMERO");
		Cliente cliente2 = new Cliente("1123457", "NOMBRES CLIENTE DOS", "APELLIDOS CLIENTE DOS", "SEGUNDO");
		Cliente cliente3 = new Cliente("1123458", "NOMBRES CLIENTE TRES", "APELLIDOS CLIENTE TRES", "TERCERO");
		Cliente cliente4 = new Cliente("1123459", "NOMBRES CLIENTE CUATRO", "APELLIDOS CLIENTE CUATRO", "CUARTO");
		Cliente cliente5 = new Cliente("1123460", "NOMBRES CLIENTE CINCO", "APELLIDOS CLIENTE CINCO", "QUINTO");
		Cliente cliente6 = new Cliente("1123461", "NOMBRES CLIENTE SEIS", "APELLIDOS CLIENTE SEIS", "SEXTO");
		Cliente cliente7 = new Cliente("1123462", "NOMBRES CLIENTE SIETE", "APELLIDOS CLIENTE SIETE", "SEPTIMO");
		Cliente cliente8 = new Cliente("1123463", "NOMBRES CLIENTE OCHO", "APELLIDOS CLIENTE OCHO", "OCTAVO");
		Cliente cliente9 = new Cliente("1123464", "NOMBRES CLIENTE NUEVE", "APELLIDOS CLIENTE NUEVE", "NOVENO");
		Cliente cliente10 = new Cliente("1123465", "NOMBRES CLIENTE DIEZ", "APELLIDOS CLIENTE DIEZ", "DECIMO");
		cliente1.setDireccion(direccion1);
		cliente2.setDireccion(direccion2);
		cliente3.setDireccion(direccion3);
		cliente4.setDireccion(direccion4);
		cliente5.setDireccion(direccion5);
		cliente6.setDireccion(direccion6);
		cliente7.setDireccion(direccion7);
		cliente8.setDireccion(direccion8);
		cliente9.setDireccion(direccion9);
		cliente10.setDireccion(direccion10);

		// -----

		TelefonoCliente t1 = new TelefonoCliente();
		t1.setNumero("1234567890");
		TelefonoCliente t2 = new TelefonoCliente();
		t2.setNumero("1134567890");
		cliente1.addTelefono(t1);
		cliente1.addTelefono(t2);

		// -----

		cobro.addCliente(cliente1);
		cobro.addCliente(cliente2);
		cobro.addCliente(cliente3);
		cobro.addCliente(cliente4);
		cobro.addCliente(cliente5);
		cobro.addCliente(cliente6);
		cobro.addCliente(cliente7);
		cobro.addCliente(cliente8);
		cobro.addCliente(cliente9);
		cobro.addCliente(cliente10);

		cobroService.save(cobro);
	}

	public void insertarCreditosDePrueba() {

		Interes interes = this.interesService.findByInteres(20);
		Perioricidad perioricidad = this.perioricidadService.findByPerioricidad("DIARIO");
		Plazo plazo = this.plazoService.findByPlazo("MENSUAL");

		Cliente cliente1 = clienteService.findByCedula("1123456");
		Cliente cliente2 = clienteService.findByCedula("1123457");
		Cliente cliente3 = clienteService.findByCedula("1123458");
		Cliente cliente4 = clienteService.findByCedula("1123459");
		Cliente cliente5 = clienteService.findByCedula("1123460");
		Cliente cliente6 = clienteService.findByCedula("1123461");
		Cliente cliente7 = clienteService.findByCedula("1123462");
		Cliente cliente8 = clienteService.findByCedula("1123463");
		Cliente cliente9 = clienteService.findByCedula("1123464");
		Cliente cliente10 = clienteService.findByCedula("1123465");
		// Se insertan los cobro debido a que no vienen en el resultado de la
		// busqueda(ya que no estamos en un contexto de persistencia)
		cliente1.setCobro(cobro);
		cliente2.setCobro(cobro);
		cliente3.setCobro(cobro);
		cliente4.setCobro(cobro);
		cliente5.setCobro(cobro);
		cliente6.setCobro(cobro);
		cliente7.setCobro(cobro);
		cliente8.setCobro(cobro);
		cliente9.setCobro(cobro);
		cliente10.setCobro(cobro);

		Credito credito1 = new Credito(100, 1, interes, perioricidad, plazo);
		Credito credito2 = new Credito(100, 2, interes, perioricidad, plazo);
		Credito credito3 = new Credito(100, 3, interes, perioricidad, plazo);
		Credito credito4 = new Credito(100, 4, interes, perioricidad, plazo);
		Credito credito5 = new Credito(100, 5, interes, perioricidad, plazo);
		Credito credito6 = new Credito(100, 6, interes, perioricidad, plazo);
		Credito credito7 = new Credito(100, 7, interes, perioricidad, plazo);
		Credito credito8 = new Credito(100, 8, interes, perioricidad, plazo);
		Credito credito9 = new Credito(100, 9, interes, perioricidad, plazo);
		Credito credito10 = new Credito(100, 5, 10, interes, perioricidad, plazo);

		cliente1.setCredito(credito1);
		cliente2.setCredito(credito2);
		cliente3.setCredito(credito3);
		cliente4.setCredito(credito4);
		cliente5.setCredito(credito5);
		cliente6.setCredito(credito6);
		cliente7.setCredito(credito7);
		cliente8.setCredito(credito8);
		cliente9.setCredito(credito9);
		cliente10.setCredito(credito10);

		clienteService.save(cliente1);
		clienteService.save(cliente2);
		clienteService.save(cliente3);
		clienteService.save(cliente4);
		clienteService.save(cliente5);
		clienteService.save(cliente6);
		clienteService.save(cliente7);
		clienteService.save(cliente8);
		clienteService.save(cliente9);
		clienteService.save(cliente10);
	}

	public void insertarLiquidacionesDePrueba() {

		Date date1 = new Date(120, 5, 1);
		Date date2 = new Date(120, 5, 2);
		Date date3 = new Date(120, 5, 3);

		Liquidacion liquidacion1 = new Liquidacion(date1, 10, 10, 10, "Esta es la PRIMERA liquidaion");
		Liquidacion liquidacion2 = new Liquidacion(date2, 10, 10, 10, "Esta es la SEGUNDA liquidaion");
		Liquidacion liquidacion3 = new Liquidacion(date3, 10, 10, 10, "Esta es la TERCERA liquidaion");
		// Liquidacion liquidacion2 = new Liquidacion(efectivo, base, gastos, nota)

		this.cobro.setLiquidaciones(new ArrayList<Liquidacion>());

		this.cobro.addLiquidacion(liquidacion1);
		this.cobro.addLiquidacion(liquidacion2);
		this.cobro.addLiquidacion(liquidacion3);

		this.cobroService.save(this.cobro);
	}

	public void realizarLiquidacionDeLaPrimeraLiquidacion() {

		Liquidacion liquidacion = null;

		try {
			// Obteniendo la primera liquidacion
			liquidacion = cobroService.findByIdWithLiquidaciones(1).getLiquidaciones().get(0);
			liquidacion.setAbonos(new ArrayList<>());
		} catch (Exception e) {
			System.err.println(
					"======================== ERROR OBTENIENDO LAS LIQUIDACIONES DEL COBRO 1 ========================");
			System.out.println("TestData.realizarLiquidacionDeLaPrimeraLiquidacion()");
			return;
		}

		Credito credito1 = creditoService.findById(1);
		Credito credito2 = creditoService.findById(2);
		Credito credito3 = creditoService.findById(3);
		Credito credito4 = creditoService.findById(4);
		Credito credito5 = creditoService.findById(5);
		Credito credito6 = creditoService.findById(6);
		Credito credito7 = creditoService.findById(7);
		Credito credito8 = creditoService.findById(8);
		Credito credito9 = creditoService.findById(9);
		Credito credito10 = creditoService.findById(10);

		Abono abono1 = new Abono(credito1, 1);
		Abono abono2 = new Abono(credito2, 2);
		Abono abono3 = new Abono(credito3, 3);
		Abono abono4 = new Abono(credito4, 4);
		Abono abono5 = new Abono(credito5, 5);
		Abono abono6 = new Abono(credito6, 6);
		Abono abono7 = new Abono(credito7, 7);
		Abono abono8 = new Abono(credito8, 8);
		Abono abono9 = new Abono(credito9, 9);
		Abono abono10 = new Abono(credito10, 10);

		liquidacion.addAbono(abono1);
		liquidacion.addAbono(abono2);
		liquidacion.addAbono(abono3);
		liquidacion.addAbono(abono4);
		liquidacion.addAbono(abono5);
		liquidacion.addAbono(abono6);
		liquidacion.addAbono(abono7);
		liquidacion.addAbono(abono8);
		liquidacion.addAbono(abono9);
		liquidacion.addAbono(abono10);

		this.liquidacionService.save(liquidacion);
	}

	public void realizarSegundaLiquidacionDeLaPrimeraLiquidacion() {

		Liquidacion liquidacion = null;

		try {
			// Obteniendo la primera liquidacion
			liquidacion = cobroService.findByIdWithLiquidaciones(1).getLiquidaciones().get(1);
			liquidacion.setAbonos(new ArrayList<>());
		} catch (Exception e) {
			System.err.println(
					"======================== ERROR OBTENIENDO LAS LIQUIDACIONES DEL COBRO 1 ========================");
			System.out.println("TestData.realizarLiquidacionDeLaPrimeraLiquidacion()");
			return;
		}

		Credito credito1 = creditoService.findById(1);
		Credito credito2 = creditoService.findById(2);
		Credito credito3 = creditoService.findById(3);
		Credito credito4 = creditoService.findById(4);
		Credito credito5 = creditoService.findById(5);
		Credito credito6 = creditoService.findById(6);
		Credito credito7 = creditoService.findById(7);
		Credito credito8 = creditoService.findById(8);
		Credito credito9 = creditoService.findById(9);
		Credito credito10 = creditoService.findById(10);

		Abono abono1 = new Abono(credito1, 10);
		Abono abono2 = new Abono(credito2, 20);
		Abono abono3 = new Abono(credito3, 30);
		Abono abono4 = new Abono(credito4, 40);
		Abono abono5 = new Abono(credito5, 50);
		Abono abono6 = new Abono(credito6, 60);
		Abono abono7 = new Abono(credito7, 70);
		Abono abono8 = new Abono(credito8, 80);
		Abono abono9 = new Abono(credito9, 90);
		Abono abono10 = new Abono(credito10, 110);

		liquidacion.addAbono(abono1);
		liquidacion.addAbono(abono2);
		liquidacion.addAbono(abono3);
		liquidacion.addAbono(abono4);
		liquidacion.addAbono(abono5);
		liquidacion.addAbono(abono6);
		liquidacion.addAbono(abono7);
		liquidacion.addAbono(abono8);
		liquidacion.addAbono(abono9);
		liquidacion.addAbono(abono10);

		this.liquidacionService.save(liquidacion);
	}

}
