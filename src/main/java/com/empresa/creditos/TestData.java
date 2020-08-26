package com.empresa.creditos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.empresa.creditos.service.IClienteService;
import com.empresa.creditos.service.ICobradorService;
import com.empresa.creditos.service.ICobroService;
import com.empresa.creditos.service.credito.IInteresService;
import com.empresa.creditos.service.credito.IPerioricidadService;
import com.empresa.creditos.service.credito.IPlazoService;
import com.empresa.creditos.service.direccion.IDepartamentoService;
import com.empresa.creditos.service.direccion.IDireccionService;
import com.empresa.creditos.service.direccion.IMunicipioService;
import com.empresa.creditos.service.liquidacion.ILiquidacionService;

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
//	@Autowired
//	private ICreditoService creditoService;
	@Autowired
	private ILiquidacionService liquidacionService;
	@Autowired
	private IClienteService clienteService;

	Departamento cordoba;
	Municipio monteria;

	@PostConstruct
	public void populateDatabase() {

		this.insertarTodosLosDepartamentosDeColombia();
		this.insertarMunicipiosDeCordoba();

		this.cordoba = this.departamentoService.findByNombre("CORDOBA");
		this.monteria = this.cordoba.getMunicipios().get(12);

		this.insertarInteresPorDefecto();
		this.insertarPlazosPorDefecto();
		this.insertarPerioricidadPorDefecto();

//		COBROS
		Cobro cobro1 = new Cobro("PRIMER COBRO ( 1 )");
		Cobro cobro2 = new Cobro("SEGUNDO COBRO ( 2 )");
		cobro1 = this.cobroService.save(cobro1);
		cobro2 = this.cobroService.save(cobro2);

//		COBRADOR
		Cobrador cobrador1 = new Cobrador(123457, "NOMBRES COBRADOR UNO", "APELLIDOS COBRADOR UNO", "EL ZARCO");
		Direccion direccionCobrador1 = new Direccion(cordoba, monteria, null,
				"AVENIDA SIEMPREVIVA, CALLE DEL INFIERNO");
		direccionCobrador1 = this.direccionService.save(direccionCobrador1);
		cobrador1.setDireccion(direccionCobrador1);
		cobrador1.setCobro(cobro1);
		cobrador1 = this.cobradorService.save(cobrador1);

//		CLIENTES
		insertarClientesDePrueba(cobro1);

		cobro1 = this.cobroService.findById(cobro1.getId());

//		CREDITOS
		insertarCreditosDePrueba(cobro1);

//		LIQUIDACIONES
		insertarLiquidacionesDePrueba(cobro1);
		realizarLiquidacionDeLaPrimeraLiquidacion(cobro1);
		realizarSegundaLiquidacionDeLaPrimeraLiquidacion(cobro1);
		
	}

	public void insertarTodosLosDepartamentosDeColombia() {

		Departamento d1 = new Departamento();
		d1.setNombre("AMAZONAS");
		Departamento d2 = new Departamento();
		d2.setNombre("ANTIOQUIA");
		Departamento d3 = new Departamento();
		d3.setNombre("ARAUCA");
		Departamento d4 = new Departamento();
		d4.setNombre("ATLANTICO");
		Departamento d5 = new Departamento();
		d5.setNombre("BOLIVAR");
		Departamento d6 = new Departamento();
		d6.setNombre("BOYACA");
		Departamento d7 = new Departamento();
		d7.setNombre("CALDAS");
		Departamento d8 = new Departamento();
		d8.setNombre("CAQUETA");
		Departamento d9 = new Departamento();
		d9.setNombre("CASANARE");
		Departamento d10 = new Departamento();
		d10.setNombre("CAUCA");
		Departamento d11 = new Departamento();
		d11.setNombre("CESAR");
		Departamento d12 = new Departamento();
		d12.setNombre("CHOCO");
		Departamento d13 = new Departamento();
		d13.setNombre("CORDOBA");
		Departamento d14 = new Departamento();
		d14.setNombre("CUNDINAMARCA");
		Departamento d15 = new Departamento();
		d15.setNombre("GUAINIA");
		Departamento d16 = new Departamento();
		d16.setNombre("GUAVIARE");
		Departamento d17 = new Departamento();
		d17.setNombre("HUILA");
		Departamento d18 = new Departamento();
		d18.setNombre("LA GUAJIRA");
		Departamento d19 = new Departamento();
		d19.setNombre("MAGDALENA");
		Departamento d20 = new Departamento();
		d20.setNombre("META");
		Departamento d21 = new Departamento();
		d21.setNombre("NARIÑO");
		Departamento d22 = new Departamento();
		d22.setNombre("NORTE DE SANTANDER");
		Departamento d23 = new Departamento();
		d23.setNombre("PUTUMAYO");
		Departamento d24 = new Departamento();
		d24.setNombre("QUINDIO");
		Departamento d25 = new Departamento();
		d25.setNombre("RISARALDA");
		Departamento d26 = new Departamento();
		d26.setNombre("SAN ANDRES Y PROVIDENCIA");
		Departamento d27 = new Departamento();
		d27.setNombre("SANTANDER");
		Departamento d28 = new Departamento();
		d28.setNombre("SUCRE");
		Departamento d29 = new Departamento();
		d29.setNombre("TOLIMA");
		Departamento d30 = new Departamento();
		d30.setNombre("VALLE DEL CAUCA");
		Departamento d31 = new Departamento();
		d31.setNombre("VAUPES");
		Departamento d32 = new Departamento();
		d32.setNombre("VICHADA");

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

		Departamento cordoba = this.departamentoService.findByNombre("CORDOBA");

		Municipio m1 = new Municipio(cordoba, "AYAPEL");
		Municipio m2 = new Municipio(cordoba, "BUENAVISTA");
		Municipio m3 = new Municipio(cordoba, "CANALETE");
		Municipio m4 = new Municipio(cordoba, "CERETE");
		Municipio m5 = new Municipio(cordoba, "CHIMA");
		Municipio m6 = new Municipio(cordoba, "CHINU");
		Municipio m7 = new Municipio(cordoba, "CIENAGA DE ORO");
		Municipio m8 = new Municipio(cordoba, "COTORRA");
		Municipio m9 = new Municipio(cordoba, "LA APARTADA");
		Municipio m10 = new Municipio(cordoba, "LOS CORDOBAS");
		Municipio m11 = new Municipio(cordoba, "MOMIL");
		Municipio m12 = new Municipio(cordoba, "MONTELIBANO");
		Municipio m13 = new Municipio(cordoba, "MONTERIA");
		Municipio m14 = new Municipio(cordoba, "MOÑITOS");
		Municipio m15 = new Municipio(cordoba, "PLANETA RICA");
		Municipio m16 = new Municipio(cordoba, "PUEBLO NUEVO");
		Municipio m17 = new Municipio(cordoba, "PUERTO ESCONDIDO");
		Municipio m18 = new Municipio(cordoba, "PUERTO LIBERTADOR");
		Municipio m19 = new Municipio(cordoba, "PURISIMA");
		Municipio m20 = new Municipio(cordoba, "SAHAGUN");
		Municipio m21 = new Municipio(cordoba, "SAN ANDRES DE SOTAVENTO");
		Municipio m22 = new Municipio(cordoba, "SAN ANTERO");
		Municipio m23 = new Municipio(cordoba, "SAN BERNARDO DEL VIENTO");
		Municipio m24 = new Municipio(cordoba, "SAN CARLOS");
		Municipio m25 = new Municipio(cordoba, "SAN JOSE DE URE");
		Municipio m26 = new Municipio(cordoba, "SAN PELAYO");
		Municipio m27 = new Municipio(cordoba, "SANTA CRUZ DE LORICA");
		Municipio m28 = new Municipio(cordoba, "TIERRALTA");
		Municipio m29 = new Municipio(cordoba, "TUCHIN");
		Municipio m30 = new Municipio(cordoba, "VALENCIA");

		this.municipioService.save(m1);
		this.municipioService.save(m2);
		this.municipioService.save(m3);
		this.municipioService.save(m4);
		this.municipioService.save(m5);
		this.municipioService.save(m6);
		this.municipioService.save(m7);
		this.municipioService.save(m8);
		this.municipioService.save(m9);
		this.municipioService.save(m10);
		this.municipioService.save(m11);
		this.municipioService.save(m12);
		this.municipioService.save(m13);
		this.municipioService.save(m14);
		this.municipioService.save(m15);
		this.municipioService.save(m16);
		this.municipioService.save(m17);
		this.municipioService.save(m18);
		this.municipioService.save(m19);
		this.municipioService.save(m20);
		this.municipioService.save(m21);
		this.municipioService.save(m22);
		this.municipioService.save(m23);
		this.municipioService.save(m24);
		this.municipioService.save(m25);
		this.municipioService.save(m26);
		this.municipioService.save(m27);
		this.municipioService.save(m28);
		this.municipioService.save(m29);
		this.municipioService.save(m30);

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

	public void insertarClientesDePrueba(Cobro cobro) {

		Cliente cliente1 = new Cliente(123456, "NOMBRES CLIENTE UNO", "APELLIDOS CLIENTE UNO", "PRIMERO", cobro);
		Cliente cliente2 = new Cliente(123457, "NOMBRES CLIENTE DOS", "APELLIDOS CLIENTE DOS", "SEGUNDO", cobro);
		Cliente cliente3 = new Cliente(123458, "NOMBRES CLIENTE TRES", "APELLIDOS CLIENTE TRES", "TERCERO", cobro);
		Cliente cliente4 = new Cliente(123459, "NOMBRES CLIENTE CUATRO", "APELLIDOS CLIENTE CUATRO", "CUARTO", cobro);
		Cliente cliente5 = new Cliente(123460, "NOMBRES CLIENTE CINCO", "APELLIDOS CLIENTE CINCO", "QUINTO", cobro);
		Cliente cliente6 = new Cliente(123461, "NOMBRES CLIENTE SEIS", "APELLIDOS CLIENTE SEIS", "SEXTO", cobro);
		Cliente cliente7 = new Cliente(123462, "NOMBRES CLIENTE SIETE", "APELLIDOS CLIENTE SIETE", "SEPTIMO", cobro);
		Cliente cliente8 = new Cliente(123463, "NOMBRES CLIENTE OCHO", "APELLIDOS CLIENTE OCHO", "OCTAVO", cobro);
		Cliente cliente9 = new Cliente(123464, "NOMBRES CLIENTE NUEVE", "APELLIDOS CLIENTE NUEVE", "NOVENO", cobro);
		Cliente cliente10 = new Cliente(123465, "NOMBRES CLIENTE DIEZ", "APELLIDOS CLIENTE DIEZ", "DECIMO", cobro);

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

		cliente1 = this.clienteService.save(cliente1);
		cliente2 = this.clienteService.save(cliente2);
		cliente3 = this.clienteService.save(cliente3);
		cliente4 = this.clienteService.save(cliente4);
		cliente5 = this.clienteService.save(cliente5);
		cliente6 = this.clienteService.save(cliente6);
		cliente7 = this.clienteService.save(cliente7);
		cliente8 = this.clienteService.save(cliente8);
		cliente9 = this.clienteService.save(cliente9);
		cliente10 = this.clienteService.save(cliente10);
	}

	public void insertarCreditosDePrueba(Cobro cobro) {

		Interes interes = this.interesService.findByInteres(20);
		Perioricidad perioricidad = this.perioricidadService.findByDescripcion("DIARIO");
		Plazo plazo = this.plazoService.findByDescripcion("MENSUAL");

		Credito credito1 = new Credito(cobro.getClientes().get(0), cobro.getCobrador(), 100, interes, perioricidad,
				plazo);
		Credito credito2 = new Credito(cobro.getClientes().get(1), cobro.getCobrador(), 100, interes, perioricidad,
				plazo);
		Credito credito3 = new Credito(cobro.getClientes().get(2), cobro.getCobrador(), 100, interes, perioricidad,
				plazo);
		Credito credito4 = new Credito(cobro.getClientes().get(3), cobro.getCobrador(), 100, interes, perioricidad,
				plazo);
		Credito credito5 = new Credito(cobro.getClientes().get(4), cobro.getCobrador(), 100, interes, perioricidad,
				plazo);
		Credito credito6 = new Credito(cobro.getClientes().get(5), cobro.getCobrador(), 100, interes, perioricidad,
				plazo);
		Credito credito7 = new Credito(cobro.getClientes().get(6), cobro.getCobrador(), 100, interes, perioricidad,
				plazo);
		Credito credito8 = new Credito(cobro.getClientes().get(7), cobro.getCobrador(), 100, interes, perioricidad,
				plazo);
		Credito credito9 = new Credito(cobro.getClientes().get(8), cobro.getCobrador(), 100, interes, perioricidad,
				plazo);
		Credito credito10 = new Credito(cobro.getClientes().get(9), cobro.getCobrador(), 100, interes, perioricidad,
				plazo);

//		credito1.setPosicionEnRuta(1);
//		credito2.setPosicionEnRuta(2);
//		credito3.setPosicionEnRuta(3);
//		credito4.setPosicionEnRuta(4);
//		credito5.setPosicionEnRuta(5);
//		credito6.setPosicionEnRuta(6);
//		credito7.setPosicionEnRuta(7);
//		credito8.setPosicionEnRuta(8);
//		credito9.setPosicionEnRuta(9);
//		credito10.setPosicionEnRuta(10);
		
		cobro.setCreditos(new ArrayList<Credito>());

		cobro.addCredito(credito1);
		cobro.addCredito(credito2);
		cobro.addCredito(credito3);
		cobro.addCredito(credito4);
		cobro.addCredito(credito5);
		cobro.addCredito(credito6);
		cobro.addCredito(credito7);
		cobro.addCredito(credito8);
		cobro.addCredito(credito9);
		cobro.addCredito(credito10);

		this.cobroService.save(cobro);
	}

	public void insertarLiquidacionesDePrueba(Cobro cobro) {

		Liquidacion liquidacion1 = new Liquidacion();
		Liquidacion liquidacion2 = new Liquidacion();

		cobro.setLiquidaciones(new ArrayList<Liquidacion>());

		cobro.addLiquidacion(liquidacion1);
		cobro.addLiquidacion(liquidacion2);

		this.cobroService.save(cobro);
	}

	public void realizarLiquidacionDeLaPrimeraLiquidacion(Cobro cobro) {

		Cobro cobro1;
		Liquidacion liquidacion;
		Cobro cobro2;
		List<Credito> creditos;

		try {
			cobro1 = this.cobroService.findByIdWithLiquidaciones(cobro.getId());
			liquidacion = cobro1.getLiquidaciones().get(0);
			System.err.println(liquidacion.toString());

		} catch (Exception e) {
			System.err.println("Error obteniendo las liquidaciones");
			System.err.println("TestData.realizarLiquidacionDeLaPrimeraLiquidacion()");
			return;
		}

		liquidacion.setAbonos(new ArrayList<Abono>());

		try {
			cobro2 = this.cobroService.findByIdWithCreditos(cobro.getId());
			creditos = (List<Credito>) cobro2.getCreditos();

			for (Credito credito : creditos) {
				System.err.println(credito.toString());
			}

		} catch (Exception e) {
			System.err.println("Error obteniendo los creditos");
			System.err.println("TestData.realizarLiquidacionDeLaPrimeraLiquidacion()");
			return;
		}

		Abono abono1 = new Abono(liquidacion, creditos.get(0), 1);
		Abono abono2 = new Abono(liquidacion, creditos.get(1), 2);
		Abono abono3 = new Abono(liquidacion, creditos.get(2), 3);
		Abono abono4 = new Abono(liquidacion, creditos.get(3), 4);
		Abono abono5 = new Abono(liquidacion, creditos.get(4), 5);
		Abono abono6 = new Abono(liquidacion, creditos.get(5), 6);
		Abono abono7 = new Abono(liquidacion, creditos.get(6), 7);
		Abono abono8 = new Abono(liquidacion, creditos.get(7), 8);
		Abono abono9 = new Abono(liquidacion, creditos.get(8), 9);
		Abono abono10 = new Abono(liquidacion, creditos.get(9), 10);

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

	public void realizarSegundaLiquidacionDeLaPrimeraLiquidacion(Cobro cobro) {

		Cobro cobro1;
		Liquidacion liquidacion;
		Cobro cobro2;
		List<Credito> creditos;

		try {
			cobro1 = this.cobroService.findByIdWithLiquidaciones(cobro.getId());
			liquidacion = cobro1.getLiquidaciones().get(1);
			System.err.println(liquidacion.toString());

		} catch (Exception e) {
			System.err.println("Error obteniendo las liquidaciones");
			System.err.println("TestData.realizarLiquidacionDeLaPrimeraLiquidacion()");
			return;
		}

		liquidacion.setAbonos(new ArrayList<Abono>());

		try {
			cobro2 = this.cobroService.findByIdWithCreditos(cobro.getId());
			creditos = (List<Credito>) cobro2.getCreditos();

			for (Credito credito : creditos) {
				System.err.println(credito.toString());
			}

		} catch (Exception e) {
			System.err.println("Error obteniendo los creditos");
			System.err.println("TestData.realizarLiquidacionDeLaPrimeraLiquidacion()");
			return;
		}

		Abono abono1 = new Abono(liquidacion, creditos.get(0), 5);
		Abono abono2 = new Abono(liquidacion, creditos.get(1), 10);
		Abono abono3 = new Abono(liquidacion, creditos.get(2), 15);
		Abono abono4 = new Abono(liquidacion, creditos.get(3), 20);
		Abono abono5 = new Abono(liquidacion, creditos.get(4), 25);
		Abono abono6 = new Abono(liquidacion, creditos.get(5), 30);
		Abono abono7 = new Abono(liquidacion, creditos.get(6), 35);
		Abono abono8 = new Abono(liquidacion, creditos.get(7), 40);
		Abono abono9 = new Abono(liquidacion, creditos.get(8), 45);
		Abono abono10 = new Abono(liquidacion, creditos.get(9), 50);

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
