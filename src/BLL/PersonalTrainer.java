package BLL;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.ControllerAlumno;
import DLL.ControllerDesafio;
import DLL.ControllerPersonalTrainer;
import DLL.ControllerRutina;

public class PersonalTrainer extends Persona{
	private int idPersonalTrainer;
	private double calificacion;
	private boolean disponiblidad;
	private LinkedList<PersonalTrainer> aCargo = new LinkedList<PersonalTrainer>();
	private LinkedList<Review> reviews = new LinkedList<Review>();
	private LinkedList<PersonalTrainer> horarios = new LinkedList<PersonalTrainer>();
	private static LinkedList<PersonalTrainer> nuevoPer = new LinkedList<PersonalTrainer>();
	
	public PersonalTrainer(int id, String nombre, String apellido, String email, String contra,
			int rol) {
		super(id, nombre, apellido, email, contra, rol);
		this.calificacion = 0;
		this.disponiblidad = true;
		this.idPersonalTrainer = 0;
	}
	public PersonalTrainer(int id, String nombre, String apellido, String email, String contra,
			int rol,int idPersonalTrainer) {
		super(id, nombre, apellido, email, contra, rol);
		this.calificacion = 0;
		this.disponiblidad = true;
		this.idPersonalTrainer = idPersonalTrainer;
	}
	public PersonalTrainer( String nombre, String apellido, String email, String contra,
			int rol) {
		super(nombre, apellido, email, contra, rol);
		this.calificacion = 0;
		this.disponiblidad = true;
		this.idPersonalTrainer = 0;
	}
	public PersonalTrainer(int idPersonalTrainer) {
		super(idPersonalTrainer);
		this.calificacion = 0;
		this.disponiblidad = true;
		this.idPersonalTrainer = idPersonalTrainer;
	}
	public PersonalTrainer(int id, int id2) {
		super(id);
		this.calificacion = 0;
		this.disponiblidad = true;
		this.idPersonalTrainer = 0;
	}
	public PersonalTrainer(int idPersonalTrainer, String nombre, String apellido) {
	    super(idPersonalTrainer, nombre, apellido); 
	    this.idPersonalTrainer = idPersonalTrainer;
	    this.calificacion = 0;
	    this.disponiblidad = true;
	}

	
	public PersonalTrainer(String nombre, String apellido) {
		super(nombre,apellido);
    }
	
	
	public double getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
	public boolean isDisponiblidad() {
		return disponiblidad;
	}
	public void setDisponiblidad(boolean disponiblidad) {
		this.disponiblidad = disponiblidad;
	}
	public LinkedList<PersonalTrainer> getaCargo() {
		return aCargo;
	}
	public void setaCargo(LinkedList<PersonalTrainer> aCargo) {
		this.aCargo = aCargo;
	}
	public LinkedList<Review> getReviews() {
		return reviews;
	}
	public void setReviews(LinkedList<Review> reviews) {
		this.reviews = reviews;
	}
	public LinkedList<PersonalTrainer> getHorarios() {
		return horarios;
	}
	public void setHorarios(LinkedList<PersonalTrainer> horarios) {
		this.horarios = horarios;
	}
	
	public static LinkedList<PersonalTrainer> getNuevoPer() {
		return nuevoPer;
	}
	public void setNuevoPer(LinkedList<PersonalTrainer> nuevoPer) {
		this.nuevoPer = nuevoPer;
	}
	
	
	public int getIdPersonalTrainer() {
		return idPersonalTrainer;
	}
	public void setIdPersonalTrainer(int idPersonalTrainer) {
		this.idPersonalTrainer = idPersonalTrainer;
	}
	

	
	@Override
	public String toString() {
		return "PersonalTrainer " + this.getNombre() +"" +this.getApellido();
	}
	public void menuPer() {
		JOptionPane.showMessageDialog(null, "Bienvenido de nuevo " +this.getNombre() + "!");
		int opcion,opcion2,opcion3,opcion4,opcion5,opcion6;
		
		String[] opciones = {
				"Rutinas", "Alumnos","Desafios","Mi Perfil","Cerrar Sesion"
		};
		
		do {
			opcion = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones, opciones[0]); 
		
		switch (opcion) {
		case 0:
			do {
				String[] opciones2 = {
						"Mis Rutinas", "Crear Rutina","Editar Rutina","Salir"
				};
				opcion2 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones2, opciones2[0]);
				switch (opcion2) {
				case 0:
					try {
						LinkedList<Rutina> misRutinas = ControllerRutina.MostrarRutinas(this.getIdPersonalTrainer());
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
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error");
					}
					
					break;

				case 1:
					String[] tipoRut = {"Personalizada","Predeterminada"};
					String eleccion = (String)JOptionPane.showInputDialog(null, "Que tipo de rutina creara?", 
							null, JOptionPane.QUESTION_MESSAGE, null, tipoRut, tipoRut[0]);
					try {
						
						if (eleccion.equals("Personalizada")) {
							this.aCargo = ControllerPersonalTrainer.MostrarAlumnos(this.getIdPersonalTrainer());
							String[] alumnos = new String[(this.aCargo.size())];
					        for (int i = 0; i < this.aCargo.size(); i++) {
					            alumnos[i] = this.aCargo.get(i).getId()+ " " +this.aCargo.get(i).getNombre() + " " + this.aCargo.get(i).getApellido();
					        }
					       try {
							
						       String seleccionado =  (String) JOptionPane.showInputDialog(null, "Elija al alumno", 
										null, JOptionPane.QUESTION_MESSAGE, null, alumnos, alumnos[0]);
						       if (seleccionado!=null) {
						    	   JOptionPane.showMessageDialog(null, "Eligio al alumno: " + seleccionado);
							}
						       
						       String[] partes = seleccionado.split(" ");
						        int idAlumnoSeleccionado = Integer.parseInt(partes[0]);
						        String titulo = JOptionPane.showInputDialog("Escriba aqui el titulo de la rutina");
						        if (titulo!=null && !titulo.isEmpty()) {
						        	String descripcion = JOptionPane.showInputDialog("Escriba aqui la rutina");
						        	if (descripcion!=null && !descripcion.isEmpty()) {
						        		ControllerRutina.agregarRutina(new Rutina(descripcion,titulo,0,idAlumnoSeleccionado,this.getIdPersonalTrainer()));
									} else {
										JOptionPane.showMessageDialog(null, "Ingrese una descripcion correctamente");
									}
								} else {
									JOptionPane.showMessageDialog(null, "Ingrese un titulo correctamente");
								}
						        
					       } catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Error, Debe elegir un alumno");
							}
					        
						} else {
							try {
								String titulo = JOptionPane.showInputDialog("Escriba aqui el titulo de la rutina");
								String descripcion = JOptionPane.showInputDialog("Escriba aqui la rutina");
						        ControllerRutina.agregarRutinaPredeterminada(new Rutina(descripcion,titulo,1,this.getIdPersonalTrainer()));
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Error");
							}
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error, debe elegir un tipo de Rutina");
					}
					break;
				case 2:
					LinkedList<Rutina> misRutinas2 = ControllerRutina.MostrarRutinas(this.getIdPersonalTrainer());
					String[] titulosRutinas2 = new String[misRutinas2.size()];
					for (int i = 0; i < misRutinas2.size(); i++) {
					    titulosRutinas2[i] = misRutinas2.get(i).getTitulo();
					}

					String tituloSeleccionado2 = (String) JOptionPane.showInputDialog(null, "Elija la rutina", 
					                        null, JOptionPane.QUESTION_MESSAGE, null, titulosRutinas2, titulosRutinas2[0]);

					Rutina rutinaSeleccionada2 = null;
					for (Rutina rutina : misRutinas2) {
					    if (rutina.getTitulo().equals(tituloSeleccionado2)) {
					        rutinaSeleccionada2 = rutina;
					        break;
					    }
					}
					JOptionPane.showMessageDialog(null, rutinaSeleccionada2);
					
					String[] editarRutina = {
							"Modificar Titulo", "Modificar Rutina","Volver"
					};
					int modificacion = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, editarRutina, editarRutina[0]);
					switch (modificacion) {
					case 0:
						try {
							boolean flag;
							String tituloNuevo;
							do {
						        flag = false;
						        tituloNuevo = JOptionPane.showInputDialog("Ingrese el Titulo de la Rutina");
						        if (tituloNuevo != null && !tituloNuevo.equals("")) {
						            flag = true;
						        } else {
						            JOptionPane.showMessageDialog(null, "El titulo no puede estar vacio. Intentelo nuevamente.");
						        }
						    } while (!flag);
							JOptionPane.showMessageDialog(null, rutinaSeleccionada2.getIdRutina());
							ControllerRutina.actualizarRutina(new Rutina(rutinaSeleccionada2.getIdRutina(),rutinaSeleccionada2.getDescripcion(),tituloNuevo));
							
						} catch (Exception e) {
						
						}
						
						
						break;

					case 1:
						try {
							boolean flag;
							String descripcionNueva;
							do {
						        flag = false;
						        descripcionNueva = JOptionPane.showInputDialog("Ingrese los ejercicios de la Rutina");
						        if (descripcionNueva != null && !descripcionNueva.equals("")) {
						            flag = true;
						        } else {
						            JOptionPane.showMessageDialog(null, "La rutina no puede estar vacia. Intentelo nuevamente.");
						        }
						    } while (!flag);
							JOptionPane.showMessageDialog(null, rutinaSeleccionada2.getIdRutina());
							ControllerRutina.actualizarRutina(new Rutina(rutinaSeleccionada2.getIdRutina(),descripcionNueva,rutinaSeleccionada2.getTitulo()));
							
						} catch (Exception e) {
						
						}
						break;
					}
					
					break;
				}
			} while (opcion2!=3);
			break;

		case 1:
			do {
				String[] opciones3 = {
						"Mis Alumnos","Salir"
				};
				opcion3 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones3, opciones3[0]);
				switch (opcion3) {
				case 0:
					JOptionPane.showMessageDialog(null, "Veo los alumnos a cargo");
					break;
				}
			} while (opcion3!=1);
			break;
		case 2:
			do {
				String[] opciones4 = {
						"Desafios Disponibles", "Crear Desafio","Eliminar Desafio","Mis Desafios","Salir"
				};
				opcion4 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones4, opciones4[0]);
				switch (opcion4) {
				case 0:
					try {
						JOptionPane.showMessageDialog(null, "Aqui veo los desafios disponibles");
						LinkedList<Desafio> desafios = ControllerDesafio.desafiosDispo();
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
					JOptionPane.showMessageDialog(null, "Aqui creo desafios");
					try {    
					    String tituloDes = JOptionPane.showInputDialog("Ingrese el titulo del desafio");
					    if (tituloDes != null && !tituloDes.isEmpty()) {
					        String descripcionDes = JOptionPane.showInputDialog("Ingrese la descripcion del desafio a realizar");
					        if (descripcionDes != null && !descripcionDes.isEmpty()) {
					            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
					            String fechaDes = JOptionPane.showInputDialog("Ingrese la fecha de expiracion del desafio en formato: (YYYY-MM-DD)");
					            if (fechaDes != null && !fechaDes.isEmpty()) {
					                Date fechaIngresada = formatoFecha.parse(fechaDes);
					                Date fechaActual = new Date();
					                if (fechaIngresada.before(fechaActual)) {
					                    JOptionPane.showMessageDialog(null, "La fecha tiene que ser mayor a la fecha actual");
					                } else {
					                    PersonalTrainer nuevoPerso = new PersonalTrainer(this.getIdPersonalTrainer());
					                    java.sql.Date sqlFechaIngresada = new java.sql.Date(fechaIngresada.getTime());
					                    ControllerDesafio.agregarDesafio(new Desafio(tituloDes, descripcionDes, sqlFechaIngresada, nuevoPerso));
					                    JOptionPane.showMessageDialog(null, "Desafio agregado exitosamente.");
					                }
					            }
					        }  
					    } 
					} catch (Exception e) {
					    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
					}
					

					
					break;
					
				case 2:
					JOptionPane.showMessageDialog(null, "Aqui puedo eliminar desafios");
					LinkedList<Desafio> misDesafios2 = ControllerDesafio.misDesafios(this.getIdPersonalTrainer());
					String[] tituloMisDes2 = new String[misDesafios2.size()];
					for (int i = 0; i < misDesafios2.size(); i++) {
						tituloMisDes2[i] = misDesafios2.get(i).getTitulo();
					}

					String tituloDesSel3 = (String) JOptionPane.showInputDialog(null, "Elija la rutina", 
					                        null, JOptionPane.QUESTION_MESSAGE, null, tituloMisDes2, tituloMisDes2[0]);

					Desafio DesSel3 = null;
					for (Desafio desafio : misDesafios2) {
					    if (desafio.getTitulo().equals(tituloDesSel3)) {
					    	DesSel3 = desafio;
					        break;
					    }
					}
					JOptionPane.showMessageDialog(null, DesSel3);
					String[] confirmacion2 = {"Confirmar","Cancelar"};
					String eleccion2 = (String)JOptionPane.showInputDialog(null, "Esta seguro que quiere eliminar este desafio?", 
							null, JOptionPane.QUESTION_MESSAGE, null, confirmacion2, confirmacion2[0]);
					if (eleccion2.equals("Confirmar")) {
						ControllerDesafio.eliminarDesafio(DesSel3.getIdDesafio());
					} 
					break;
				case 3:
					try {
						JOptionPane.showMessageDialog(null, "Aqui veo los desafios que cree yo");
						LinkedList<Desafio> misDesafios = ControllerDesafio.misDesafios(this.getIdPersonalTrainer());
						String[] tituloMisDes = new String[misDesafios.size()];
						for (int i = 0; i < misDesafios.size(); i++) {
							tituloMisDes[i] = misDesafios.get(i).getTitulo();
						}

						String tituloDesSel2 = (String) JOptionPane.showInputDialog(null, "Elija la rutina", 
						                        null, JOptionPane.QUESTION_MESSAGE, null, tituloMisDes, tituloMisDes[0]);

						Desafio DesSel2 = null;
						for (Desafio desafio : misDesafios) {
						    if (desafio.getTitulo().equals(tituloDesSel2)) {
						    	DesSel2 = desafio;
						        break;
						    }
						}
						JOptionPane.showMessageDialog(null, DesSel2);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "No creaste ningun desafio aun");
					}
					
					break;
				}
			} while (opcion4!=4);
			break;
		case 3:
			do {
				String[] opciones5 = {
						"Ver Perfil", "Editar Perfil","Salir"
				};
				opcion5 = JOptionPane.showOptionDialog(null, "Elegi una opcion", null, 0, 0, null, opciones5, opciones5[0]);
				switch (opcion5) {
				case 0:
					JOptionPane.showMessageDialog(null, this.toString());
					break;

				case 1:
					JOptionPane.showMessageDialog(null, "Aqui edito mi perfil");
					break;
				}
			} while (opcion5!=2);
		}
		} while (opcion!=4);
		
	}
	
	
	
	

	
	
}
