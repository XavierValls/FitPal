package BLL;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.ControllerAlumno;
import DLL.ControllerPersonalTrainer;
import DLL.ControllerRutina;

public class Alumno extends Persona {
	private int idAlumno;
	private double peso;
	private int altura;
	private String nivel;
	private Objetivo objPersonal;
	
	private LinkedList<PersonalTrainer> contratado = new LinkedList<PersonalTrainer>();
	private static LinkedList<Alumno> nuevoAlu = new LinkedList<Alumno>();

	public Alumno(int id,String nombre, String apellido, String email, String contra, int rol) {
		super(id,nombre, apellido, email, contra, rol);
		this.peso = peso;
		this.altura = altura;
		this.nivel = nivel;
	}
	public Alumno(int id,String nombre, String apellido, String email, String contra, int rol, int idAlumno) {
		super(id,nombre, apellido, email, contra, rol);
		this.peso = peso;
		this.altura = altura;
		this.nivel = nivel;
		this.idAlumno = idAlumno;
	}
	
	public Alumno(String nombre, String apellido, String email, String contra, int rol) {
		super(nombre, apellido, email, contra, rol);
		this.peso = peso;
		this.altura = altura;
		this.nivel = nivel;
	}
	
	
	public Alumno(int id) {
		super(id);
		this.peso = peso;
		this.altura = altura;
		this.nivel = nivel;
	}

	public Alumno() {
		super("nombre","apellido","email","contra",2);
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Objetivo getObjPersonal() {
		return objPersonal;
	}

	public void setObjPersonal(Objetivo objPersonal) {
		this.objPersonal = objPersonal;
	}

	public LinkedList<PersonalTrainer> getContratado() {
		return contratado;
	}

	public void setContratado(LinkedList<PersonalTrainer> contratado) {
		this.contratado = contratado;
	}
	

	public static LinkedList<Alumno> getNuevoAlu() {
		return nuevoAlu;
	}

	public void setNuevoAlu(LinkedList<Alumno> nuevoAlu) {
		this.nuevoAlu = nuevoAlu;
	}
	


	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}


	
	@Override
	public String toString() {
		return "Alumno [idAlumno=" + idAlumno + ", peso=" + peso + ", altura=" + altura + ", nivel=" + nivel
				+ ", objPersonal=" + objPersonal + ", contratado=" + contratado + "]";
	}
	public void menuAlu() {
		JOptionPane.showMessageDialog(null, "Bienvenido de nuevo " +this.getNombre() + "!");
		int opcion,opcion2,opcion3,opcion4,opcion5,opcion6,editarOpc;
		
		String[] opciones = {
				"Rutinas", "Objetivo","PersonalTrainer","Desafios","Mi Perfil","Cerrar Sesion"
		};
		
		do {
			opcion = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones, opciones[0]); 
		
		switch (opcion) {
		case 0:
			do {
				String[] opciones2 = {
						"Mis Rutinas", "Rutinas Predeterminadas","Salir"
				};
				opcion2 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones2, opciones2[0]);
				switch (opcion2) {
				case 0:
					LinkedList<Rutina> misRutinas = ControllerRutina.MostrarRutinas(this.getIdAlumno());
					String[] titulosRutinas = new String[misRutinas.size()];
					for (int i = 0; i < misRutinas.size(); i++) {
					    titulosRutinas[i] = misRutinas.get(i).getTitulo();
					}

					String tituloSeleccionado = (String) JOptionPane.showInputDialog(null, "Elija la rutina", 
					                        null, JOptionPane.QUESTION_MESSAGE, null, titulosRutinas, titulosRutinas[0]);

					Rutina rutinaSeleccionada = null;
					for (Rutina rutina : misRutinas) {
					    if (rutina.getTitulo().equals(tituloSeleccionado)) {
					        rutinaSeleccionada = rutina;
					        break;
					    }
					}
					JOptionPane.showMessageDialog(null, rutinaSeleccionada);
					break;

				case 1:
					LinkedList<Rutina> RutinasPredeterminadas = ControllerRutina.MostrarRutinasPredeterminadas();
					String[] titulosRutinas2 = new String[RutinasPredeterminadas.size()];
					for (int i = 0; i < RutinasPredeterminadas.size(); i++) {
					    titulosRutinas2[i] = RutinasPredeterminadas.get(i).getTitulo();
					}

					String tituloSeleccionado2 = (String) JOptionPane.showInputDialog(null, "Elija la rutina", 
					                        null, JOptionPane.QUESTION_MESSAGE, null, titulosRutinas2, titulosRutinas2[0]);

					Rutina rutinaSeleccionada2 = null;
					for (Rutina rutina : RutinasPredeterminadas) {
					    if (rutina.getTitulo().equals(tituloSeleccionado2)) {
					        rutinaSeleccionada2 = rutina;
					        break;
					    }
					}
					JOptionPane.showMessageDialog(null, rutinaSeleccionada2);
					break;
				}
			} while (opcion2!=2);
			break;

		case 1:
			do {
				String[] opciones3 = {
						"Mi Objetivo", "Establecer Objetivo","Editar Objetivo","Salir"
				};
				opcion3 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones3, opciones3[0]);
				switch (opcion3) {
				case 0:
					JOptionPane.showMessageDialog(null, ControllerAlumno.mostrarObjetivoDelAlumno(this.getIdAlumno()));
					break;

				case 1:
					JOptionPane.showMessageDialog(null, "Establezco mi Objetivo aqui");
					String[] objetivos = {
							"Ganar Masa Muscular", "Perder Grasa","Subir de Peso","Bajar de Peso","Mantenerme en mi peso","Otro objetivo","Volver"
					};
					String objetivoElegido = (String) JOptionPane.showInputDialog(null, "Elija Su Objetivo", 
	                        null, JOptionPane.QUESTION_MESSAGE, null, objetivos, objetivos[0]);
					try {
						if (objetivoElegido!=null) {
							if (objetivoElegido.equals("Otro objetivo")) {
								String otroObj = JOptionPane.showInputDialog("Ingrese su Objetivo");
								String fechaObj = JOptionPane.showInputDialog("Ingrese la fecha fin de su Objetivo de la siguiente manera: (YYYY-MM-DD)");
								if (fechaObj != null && !fechaObj.isEmpty()) {
									LocalDate fechaIngresada = LocalDate.parse(fechaObj);
									 LocalDate fechaActual = LocalDate.now();
									if (fechaIngresada.isBefore(fechaActual)) {
										JOptionPane.showMessageDialog(null, "La fecha tiene que ser mayor a la fecha actual");
									} else {
										ControllerAlumno.AgregarObjetivo(otroObj, fechaIngresada,this.getIdAlumno());
										
									}
								} else {
									JOptionPane.showMessageDialog(null, "Error, Ingrese la fecha de manera correcta");
								}
							}
							String fechaObj = JOptionPane.showInputDialog("Ingrese la fecha fin de su Objetivo de la siguiente manera: (YYYY-MM-DD)");
							if (fechaObj != null && !fechaObj.isEmpty()) {
								LocalDate fechaIngresada = LocalDate.parse(fechaObj);
								 LocalDate fechaActual = LocalDate.now();
									if (fechaIngresada.isBefore(fechaActual)) {
										JOptionPane.showMessageDialog(null, "La fecha tiene que ser mayor a la fecha actual");
									} else {
										ControllerAlumno.AgregarObjetivo(objetivoElegido, fechaIngresada,this.getIdAlumno());
									}
							} else {
								JOptionPane.showMessageDialog(null, "Error, Ingrese la fecha de manera correcta");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Debe elegir un objetivo");
						}
						
			
						
						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					
					break;
					
				case 2:
					JOptionPane.showMessageDialog(null, "Edito mi Objetivo aqui");
						try {
							String[] editarObj = {
									"Cambiar Objetivo","Cambiar la fecha del Objetivo","Volver"
							};
							editarOpc = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, editarObj, editarObj[0]);
							switch (editarOpc) {
							case 0:
								String[] objetivos2 = {
										"Ganar Masa Muscular", "Perder Grasa","Subir de Peso","Bajar de Peso","Mantenerme en mi peso","Otro objetivo","Volver"
								};
								String objetivoElegido2 = (String) JOptionPane.showInputDialog(null, "Elija Su Objetivo", 
				                        null, JOptionPane.QUESTION_MESSAGE, null, objetivos2, objetivos2[0]);
								if (objetivoElegido2.equals("Otro objetivo")) {
									String otroObj2 = JOptionPane.showInputDialog("Ingrese su Objetivo");
									if (otroObj2!=null) {
										Objetivo viejo = ControllerAlumno.mostrarObjetivoDelAlumno(this.getIdAlumno());
										
										ControllerAlumno.actualizarObjetivo(new Objetivo(viejo.getIdObjetivo(),otroObj2,viejo.getFechaCreacion(),viejo.getFechaObj()));
									}
									
								} else {
									Objetivo viejo = ControllerAlumno.mostrarObjetivoDelAlumno(this.getIdAlumno());
									ControllerAlumno.actualizarObjetivo(new Objetivo(viejo.getIdObjetivo(),objetivoElegido2,viejo.getFechaCreacion(),viejo.getFechaObj()));
								}
								
								break;

							case 1:
								String fechaObj2 = JOptionPane.showInputDialog("Ingrese la fecha fin de su Objetivo de la siguiente manera: (YYYY-MM-DD)");
								if (fechaObj2 != null && !fechaObj2.isEmpty()) {
									LocalDate fechaIngresada2 = LocalDate.parse(fechaObj2);
									 LocalDate fechaActual2 = LocalDate.now();
										if (fechaIngresada2.isBefore(fechaActual2)) {
											JOptionPane.showMessageDialog(null, "La fecha tiene que ser mayor a la fecha actual");
										} else {
											Objetivo viejo = ControllerAlumno.mostrarObjetivoDelAlumno(this.getIdAlumno());
											ControllerAlumno.actualizarObjetivo(new Objetivo(viejo.getIdObjetivo(),viejo.getDescripcion(),viejo.getFechaCreacion(),fechaIngresada2));
										}
								} else {
									JOptionPane.showMessageDialog(null, "Error, Ingrese la fecha de manera correcta");
								}
								break;
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					
					break;
				}
			} while (opcion3!=3);
			break;
		case 2:
			do {
				String[] opciones4 = {
						"Contratados", "Contratar","Dejar Review","Salir"
				};
				opcion4 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones4, opciones4[0]);
				switch (opcion4) {
				case 0:
					
					JOptionPane.showMessageDialog(null, ControllerAlumno.mostrarContratosPorAlumno(this.getIdAlumno()));
					break;

				case 1:
					LinkedList<PersonalTrainer> Personals = ControllerPersonalTrainer.MostrarPersonalTrainers();
					String[] personals = new String[Personals.size()];
					for (int i = 0; i < Personals.size(); i++) {
					    personals[i] = Personals.get(i).getNombre() + " " + Personals.get(i).getApellido();
					}

					String nombreElegido = (String) JOptionPane.showInputDialog(null, "Elija al Personal Trainer", 
					                        null, JOptionPane.QUESTION_MESSAGE, null, personals, personals[0]);

					PersonalTrainer personalElegido = null;
					for (PersonalTrainer personal : Personals) {
					    String nombreCompleto = personal.getNombre() + " " + personal.getApellido();
					    if (nombreCompleto.equals(nombreElegido)) {
					        personalElegido = personal;
					        break;
					    }
					}
					if (personalElegido!=null) {
						JOptionPane.showMessageDialog(null, personalElegido);
						String[] confirmacion = {
								"Confirmar", "Cancelar"
						};
						int eleccion = JOptionPane.showOptionDialog(null, "Contrataras al " + personalElegido + " Durante 1 mes", null, 0, 0, null, confirmacion, confirmacion[0]);
						switch (eleccion) {
						case 0:
							ControllerAlumno.agregarContrato(personalElegido.getIdPersonalTrainer(), this.getIdAlumno());
							break;
							
						default:
							break;
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe elegir un Personal Trainer");
					}
					
					break;
					
				case 2:
					JOptionPane.showMessageDialog(null, "Aqui puedo dejar una review");
					break;
				}
			} while (opcion4!=3);
			break;
		case 3:
			do {
				String[] opciones5 = {
						"Ver Desafios Disponibles", "Anotarse a un desafio","Desafios Activos","Salir"
				};
				opcion5 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones5, opciones5[0]);
				switch (opcion5) {
				case 0:
					try {
						JOptionPane.showMessageDialog(null, "Aqui veo los desafios disponibles");
						LinkedList<Desafio> desafios = ControllerPersonalTrainer.desafiosDispo();
						String[] tituloDes2 = new String[desafios.size()];
						for (int i = 0; i < desafios.size(); i++) {
							tituloDes2[i] = desafios.get(i).getTitulo();
						}

						String tituloDesSel = (String) JOptionPane.showInputDialog(null, "Elija la rutina", 
						                        null, JOptionPane.QUESTION_MESSAGE, null, tituloDes2, tituloDes2[0]);

						Desafio DesSel = null;
						for (Desafio desafio : desafios) {
						    if (desafio.getTitulo().equals(tituloDesSel)) {
						    	DesSel = desafio;
						        break;
						    }
						}
						JOptionPane.showMessageDialog(null, DesSel);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "No hay ningun desafio disponible");
					}
					break;

				case 1:
					JOptionPane.showMessageDialog(null, "Aqui me anoto a un desafio");
					try {
						JOptionPane.showMessageDialog(null, "Aqui veo los desafios disponibles");
						LinkedList<Desafio> desafios = ControllerPersonalTrainer.desafiosDispo();
						String[] tituloDes2 = new String[desafios.size()];
						for (int i = 0; i < desafios.size(); i++) {
							tituloDes2[i] = desafios.get(i).getTitulo();
						}

						String tituloDesSel = (String) JOptionPane.showInputDialog(null, "Elija la rutina", 
						                        null, JOptionPane.QUESTION_MESSAGE, null, tituloDes2, tituloDes2[0]);

						Desafio DesSel = null;
						for (Desafio desafio : desafios) {
						    if (desafio.getTitulo().equals(tituloDesSel)) {
						    	DesSel = desafio;
						        break;
						    }
						}
						JOptionPane.showMessageDialog(null, DesSel);
						try {
							String[] confirmacion2 = {"Confirmar","Cancelar"};
							String eleccion2 = (String)JOptionPane.showInputDialog(null, "Esta seguro que desea anotarse a este desafio?", 
									null, JOptionPane.QUESTION_MESSAGE, null, confirmacion2, confirmacion2[0]);
							if (eleccion2.equals("Confirmar")) {
								ControllerAlumno.inscribirseEnDesafio(this.getIdAlumno(), DesSel.getIdDesafio());
							} 
						} catch (Exception e) {
							// TODO: handle exception
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "No hay ningun desafio disponible");
					}
					break;
					
				case 2:
					JOptionPane.showMessageDialog(null, "Aqui veo los desafios a los que me anote");
					try {
						JOptionPane.showMessageDialog(null, "Aqui veo los desafios en los que me anote");
						LinkedList<Desafio> desafios = ControllerAlumno.MisDesafios(this.getIdAlumno());
						String[] tituloDes2 = new String[desafios.size()];
						for (int i = 0; i < desafios.size(); i++) {
							tituloDes2[i] = desafios.get(i).getTitulo();
						}

						String tituloDesSel = (String) JOptionPane.showInputDialog(null, "Elija la rutina", 
						                        null, JOptionPane.QUESTION_MESSAGE, null, tituloDes2, tituloDes2[0]);

						Desafio DesSel = null;
						for (Desafio desafio : desafios) {
						    if (desafio.getTitulo().equals(tituloDesSel)) {
						    	DesSel = desafio;
						        break;
						    }
						}
						JOptionPane.showMessageDialog(null, DesSel);
					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				}
			} while (opcion5!=3);
			break;
		case 4:
			do {
				String[] opciones6 = {
						"Ver Perfil", "Editar Perfil","Salir"
				};
				opcion6 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones6, opciones6[0]);
				switch (opcion6) {
				case 0:
					JOptionPane.showMessageDialog(null, this.toString());
					break;

				case 1:
					JOptionPane.showMessageDialog(null, "Aqui edito mi perfil");
					break;
				}
			} while (opcion6!=2);
		}
		} while (opcion!=5);
		
	}

	

	

	
	
}
