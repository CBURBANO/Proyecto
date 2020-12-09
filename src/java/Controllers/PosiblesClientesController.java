/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.clsFuentePosibleCliente;
import Models.clsPosiblesClientes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CAROLINA
 */
@WebServlet(name = "PosiblesClientesController", urlPatterns = {"/PosiblesClientesController"})
public class PosiblesClientesController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("btnGuardar") != null) {
            btnGuardar(request, response);
        } else if (request.getParameter("btnModificar") != null) {
            btnModificar(request, response);
        } else if (request.getParameter("btnCancelar") != null) {
        } else if (request.getParameter("codigoSeleccionado") != null) {
            if (request.getParameter("stOpcion").equals("M")) {
                cargarModificar(request, response);
            } else if (request.getParameter("stOpcion").equals("E")) {
                btnEliminar(request, response);
            }
        }
    }
    
     public void btnModificar(HttpServletRequest request, 
           HttpServletResponse response) throws IOException, IOException, ServletException{
     
         try{
             List<Models.clsPosiblesClientes> lstclsPosiblesClientes = new ArrayList<Models.clsPosiblesClientes>();
             
             HttpSession session = request.getSession(true);
         if (session.getAttribute("session_lstclsPosiblesClientes") != null){
                lstclsPosiblesClientes = (List<Models.clsPosiblesClientes>)session.getAttribute("session_lstclsPosiblesClientes");
       }
        int inPosicion =0;      
        for(clsPosiblesClientes elem : lstclsPosiblesClientes){
          if(elem.getInCodigo() == Integer.parseInt(request.getParameter("codigoModificar"))){
              break;
          }
        
          
          inPosicion++;
        }   
           
            Models.clsFuentePosibleCliente obclsFuentePosibleCliente = new Models.clsFuentePosibleCliente();
            Models.clsEstadoPosibleCliente obclsEstadoPosibleCliente = new Models.clsEstadoPosibleCliente();
            Models.clsSector obclsSector = new Models.clsSector();
            Models.clsCalificacion obclsCalificacion = new Models.clsCalificacion();
            //asignacion de atributos 
            if (request.getParameter("txtEmpresa") != null) {

                lstclsPosiblesClientes.get(inPosicion).setStEmpresa(request.getParameter("txtEmpresa"));
            }
            if (request.getParameter("txtNombre") != null) {

                  lstclsPosiblesClientes.get(inPosicion).setStNombre(request.getParameter("txtNombre"));
            }
            if (request.getParameter("txtApellidos") != null) {

                  lstclsPosiblesClientes.get(inPosicion).setStApellidos(request.getParameter("txtApellidos"));
            }
            if (request.getParameter("txtTitulo") != null) {

                  lstclsPosiblesClientes.get(inPosicion).setStTitulo(request.getParameter("txtTitulo"));
            }
            if (request.getParameter("txtCorreoElectronico") != null) {

                  lstclsPosiblesClientes.get(inPosicion).setStCorreoElectronico(request.getParameter("txtCorreoElectronico"));
            }
            if (request.getParameter("txtTelefono") != null) {

                lstclsPosiblesClientes.get(inPosicion).setStTelefono(request.getParameter("txtTelefono"));
            }
            if (request.getParameter("txtFax") != null) {

                lstclsPosiblesClientes.get(inPosicion).setStFax(request.getParameter("txtFax"));
            }
            if (request.getParameter("txtMovil") != null) {

                lstclsPosiblesClientes.get(inPosicion).setStMovil(request.getParameter("txtMovil"));
            }
            if (request.getParameter("txtWeb") != null) {

                lstclsPosiblesClientes.get(inPosicion).setStSitioWeb(request.getParameter("txtWeb"));
            }
            if (request.getParameter("ddlFuentePosibleCliente") != null) {
                //modelo hijo
                obclsFuentePosibleCliente.setInCodigo(Integer.parseInt(request.getParameter("ddlFuentePosibleCliente")));

                String stDescripcion = "";

                if (request.getParameter("ddlFuentePosibleCliente").equals("1")) {
                    stDescripcion = "None";

                } else if (request.getParameter("ddlFuentePosibleCliente").equals("2")) {
                    stDescripcion = "Aviso";
                } else if (request.getParameter("ddlFuentePosibleCliente").equals("3")) {
                    stDescripcion = "Llamada no solicitada";
                } else if (request.getParameter("ddlFuentePosibleCliente").equals("4")) {
                    stDescripcion = "Recomendacion de empleado";
                } else if (request.getParameter("ddlFuentePosibleCliente").equals("5")) {
                    stDescripcion = "Recomendacion externa";
                } else if (request.getParameter("ddlFuentePosibleCliente").equals("6")) {
                    stDescripcion = "Tienda en linea";
                }

                obclsFuentePosibleCliente.setStDescripcion(stDescripcion);

                //lo asigno al modelo padre
                lstclsPosiblesClientes.get(inPosicion).setObclsFuentePosibleCliente(obclsFuentePosibleCliente);
            }//fin if hijo

            if (request.getParameter("ddlEstadoPosibleCliente") != null) {
                //modelo hijo
                obclsEstadoPosibleCliente.setInCodigo(Integer.parseInt(request.getParameter("ddlEstadoPosibleCliente")));

                String stDescripcion = "";

                if (request.getParameter("ddlEstadoPosibleCliente").equals("1")) {
                    stDescripcion = "None";

                } else if (request.getParameter("ddlEstadoPosibleCliente").equals("2")) {
                    stDescripcion = "Intento de contacto";
                } else if (request.getParameter("ddlEstadoPosibleCliente").equals("3")) {
                    stDescripcion = "Contactar en el futuro";
                } else if (request.getParameter("ddlEstadoPosibleCliente").equals("4")) {
                    stDescripcion = "Contactado";
                } else if (request.getParameter("ddlEstadoPosibleCliente").equals("5")) {
                    stDescripcion = "Posible cliente no solicitado";
                } else if (request.getParameter("ddlEstadoPosibleCliente").equals("6")) {
                    stDescripcion = "Posible cliente perdido";
                }

                obclsEstadoPosibleCliente.setStDescripcion(stDescripcion);

                //lo asigno al modelo padre
                lstclsPosiblesClientes.get(inPosicion).setObclsEstadoPosibleCliente(obclsEstadoPosibleCliente);
            }//fin if hijo

            if (request.getParameter("ddlSector") != null) {
                //modelo hijo
                obclsSector.setInCodigo(Integer.parseInt(request.getParameter("ddlSector")));

                String stDescripcion = "";

                if (request.getParameter("ddlSector").equals("1")) {
                    stDescripcion = "None";

                } else if (request.getParameter("ddlSector").equals("2")) {
                    stDescripcion = "APS(Proveedro de servicio de aplicaciones)";
                } else if (request.getParameter("ddlSector").equals("3")) {
                    stDescripcion = "OEM de datos";
                } else if (request.getParameter("ddlSector").equals("4")) {
                    stDescripcion = "ERP(Planificacion de recursos de empresa)";
                } else if (request.getParameter("ddlSector").equals("5")) {
                    stDescripcion = "Gobierno/Ejercito";
                } else if (request.getParameter("ddlSector").equals("6")) {
                    stDescripcion = "Empresa grande";
                }

                obclsSector.setStDescripcion(stDescripcion);

                //lo asigno al modelo padre
                lstclsPosiblesClientes.get(inPosicion).setObclsSector(obclsSector);
            }//fin if hijo

            if (request.getParameter("txtCantidadEmpleados") != null) {

                lstclsPosiblesClientes.get(inPosicion).setInCatidadEmpleados(Integer.parseInt(request.getParameter("txtCantidadEmpleados")));
            }
            if (request.getParameter("txtIngresosAnuales") != null) {

                lstclsPosiblesClientes.get(inPosicion).setDbIngresosAnuales(Double.parseDouble(request.getParameter("txtIngresosAnuales")));
            }

            if (request.getParameter("ddlCalificacion") != null) {
                //modelo hijo
                obclsCalificacion.setInCodigo(Integer.parseInt(request.getParameter("ddlCalificacion")));

                String stDescripcion = "";

                if (request.getParameter("ddlCalificacion").equals("1")) {
                    stDescripcion = "None";

                } else if (request.getParameter("ddlCalificacion").equals("2")) {
                    stDescripcion = "Adquirido";
                } else if (request.getParameter("ddlSector").equals("3")) {
                    stDescripcion = "Activo";
                } else if (request.getParameter("ddlCalificacion").equals("4")) {
                    stDescripcion = "Conctactado";
                } else if (request.getParameter("ddlCalificacion").equals("5")) {
                    stDescripcion = "Fallo de mercado";
                } else if (request.getParameter("ddlCalificacion").equals("6")) {
                    stDescripcion = "Proyecto cancelado";
                } else if (request.getParameter("ddlCalificacion").equals("7")) {
                    stDescripcion = "Apagar";
                }

                obclsCalificacion.setStDescripcion(stDescripcion);

                //lo asigno al modelo padre
                lstclsPosiblesClientes.get(inPosicion).setObclsCalificacion(obclsCalificacion);
            }//fin if hijo

            if (request.getParameter("chkNoParticipacionCorreoElectronico") != null) {

                char chSeleccion = request.getParameter("chkNoParticipacionCorreoElectronico").equals("on")
                        ? 'S' : 'N';

                lstclsPosiblesClientes.get(inPosicion).setChNlNoParticipacionCorreoElectronico(chSeleccion);
            } else {
                lstclsPosiblesClientes.get(inPosicion).setChNlNoParticipacionCorreoElectronico('N');
            }

            if (request.getParameter("txtIDkype") != null) {

                lstclsPosiblesClientes.get(inPosicion).setStIDSkype(request.getParameter("txtIDkype"));
            }
            if (request.getParameter("txtTwitter") != null) {

                lstclsPosiblesClientes.get(inPosicion).setStTwitter(request.getParameter("txtTwitter"));
            }
            if (request.getParameter("txtCorreoElectronicoSecundario") != null) {

                lstclsPosiblesClientes.get(inPosicion).setStCorreoElectronicoSecundario(request.getParameter("txtCorreoElectronicoSecundario"));
            }
            
            session.setAttribute("session_lstclsPosiblesClientes", lstclsPosiblesClientes);

            //definicion parametros desde el controlador
            request.setAttribute("stMensaje", "Se realizo Proceso con exito");
            request.setAttribute("stTipo", "success");

            //redireccion y envio de valores
            request.getRequestDispatcher("PosiblesClientes.jsp").forward(request, response);
            
            
            
         }catch(Exception ex){
             request.setAttribute("stMensaje", ex.getMessage());
             request.setAttribute("stTipo", "error");
          
           request.getRequestDispatcher("PosiblesClientes.jsp").forward(request, response);
         }
     
     }
    
    

    public void btnEliminar(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        try {

            Models.clsPosiblesClientes obclsPosiblesClientes = new Models.clsPosiblesClientes();

            List<Models.clsPosiblesClientes> lstclsPosiblesClientes = new ArrayList<Models.clsPosiblesClientes>();

            HttpSession session = request.getSession(true);

            if (session.getAttribute("session_lstclsPosiblesClientes") != null) {
                lstclsPosiblesClientes = (List<Models.clsPosiblesClientes>) session.getAttribute("session_lstclsPosiblesClientes");

            }

            for (Models.clsPosiblesClientes item : lstclsPosiblesClientes) {
                if (item.getInCodigo() == Integer.parseInt(request.getParameter("codigoSeleccionado"))) {
                    obclsPosiblesClientes = item;
                    lstclsPosiblesClientes.remove(item);
                    break;
                }

            }

            session.setAttribute("session_lstclsPosiblesClientes", lstclsPosiblesClientes);
            request.setAttribute("stTipo", "success");
            request.setAttribute("stMensaje", "Se realizo proceso con exito");
            request.getRequestDispatcher("PosiblesClientes.jsp").forward(request, response);

        } catch (Exception ex) {

            request.setAttribute("stTipo", "error");
            request.setAttribute("stMensaje", ex.getMessage());
            request.getRequestDispatcher("PosiblesClientes.jsp").forward(request, response);

        }

    }

    public void cargarModificar(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        try {
            Models.clsPosiblesClientes obclsPosiblesClientes = new Models.clsPosiblesClientes();

            List<Models.clsPosiblesClientes> lstclsPosiblesClientes = new ArrayList<Models.clsPosiblesClientes>();

            HttpSession session = request.getSession(true);

            if (session.getAttribute("session_lstclsPosiblesClientes") != null) {
                lstclsPosiblesClientes = (List<Models.clsPosiblesClientes>) session.getAttribute("session_lstclsPosiblesClientes");

            }
            for (Models.clsPosiblesClientes item : lstclsPosiblesClientes) {
                if (item.getInCodigo() == Integer.parseInt(request.getParameter("codigoSeleccionado"))) {
                    obclsPosiblesClientes = item;
                }

            }

            request.setAttribute("obclsPosiblesClientes", obclsPosiblesClientes);
            request.getRequestDispatcher("PosiblesClientes.jsp").forward(request, response);

        } catch (Exception ex) {

            request.setAttribute("stTipo", "error");
            request.setAttribute("stMensaje", ex.getMessage());
            request.getRequestDispatcher("PosiblesClientes.jsp").forward(request, response);

        }
    }

    public void btnGuardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //definicion de modelos
            Models.clsPosiblesClientes obclsPosiblesClientes = new Models.clsPosiblesClientes();

            Models.clsFuentePosibleCliente obclsFuentePosibleCliente = new Models.clsFuentePosibleCliente();
            Models.clsEstadoPosibleCliente obclsEstadoPosibleCliente = new Models.clsEstadoPosibleCliente();
            Models.clsSector obclsSector = new Models.clsSector();
            Models.clsCalificacion obclsCalificacion = new Models.clsCalificacion();
            //asignacion de atributos 
            if (request.getParameter("txtEmpresa") != null) {

                obclsPosiblesClientes.setStEmpresa(request.getParameter("txtEmpresa"));
            }
            if (request.getParameter("txtNombre") != null) {

                obclsPosiblesClientes.setStNombre(request.getParameter("txtNombre"));
            }
            if (request.getParameter("txtApellidos") != null) {

                obclsPosiblesClientes.setStApellidos(request.getParameter("txtApellidos"));
            }
            if (request.getParameter("txtTitulo") != null) {

                obclsPosiblesClientes.setStTitulo(request.getParameter("txtTitulo"));
            }
            if (request.getParameter("txtCorreoElectronico") != null) {

                obclsPosiblesClientes.setStCorreoElectronico(request.getParameter("txtCorreoElectronico"));
            }
            if (request.getParameter("txtTelefono") != null) {

                obclsPosiblesClientes.setStTelefono(request.getParameter("txtTelefono"));
            }
            if (request.getParameter("txtFax") != null) {

                obclsPosiblesClientes.setStFax(request.getParameter("txtFax"));
            }
            if (request.getParameter("txtMovil") != null) {

                obclsPosiblesClientes.setStMovil(request.getParameter("txtMovil"));
            }
            if (request.getParameter("txtWeb") != null) {

                obclsPosiblesClientes.setStSitioWeb(request.getParameter("txtWeb"));
            }
            if (request.getParameter("ddlFuentePosibleCliente") != null) {
                //modelo hijo
                obclsFuentePosibleCliente.setInCodigo(Integer.parseInt(request.getParameter("ddlFuentePosibleCliente")));

                String stDescripcion = "";

                if (request.getParameter("ddlFuentePosibleCliente").equals("1")) {
                    stDescripcion = "None";

                } else if (request.getParameter("ddlFuentePosibleCliente").equals("2")) {
                    stDescripcion = "Aviso";
                } else if (request.getParameter("ddlFuentePosibleCliente").equals("3")) {
                    stDescripcion = "Llamada no solicitada";
                } else if (request.getParameter("ddlFuentePosibleCliente").equals("4")) {
                    stDescripcion = "Recomendacion de empleado";
                } else if (request.getParameter("ddlFuentePosibleCliente").equals("5")) {
                    stDescripcion = "Recomendacion externa";
                } else if (request.getParameter("ddlFuentePosibleCliente").equals("6")) {
                    stDescripcion = "Tienda en linea";
                }

                obclsFuentePosibleCliente.setStDescripcion(stDescripcion);

                //lo asigno al modelo padre
                obclsPosiblesClientes.setObclsFuentePosibleCliente(obclsFuentePosibleCliente);
            }//fin if hijo

            if (request.getParameter("ddlEstadoPosibleCliente") != null) {
                //modelo hijo
                obclsEstadoPosibleCliente.setInCodigo(Integer.parseInt(request.getParameter("ddlEstadoPosibleCliente")));

                String stDescripcion = "";

                if (request.getParameter("ddlEstadoPosibleCliente").equals("1")) {
                    stDescripcion = "None";

                } else if (request.getParameter("ddlEstadoPosibleCliente").equals("2")) {
                    stDescripcion = "Intento de contacto";
                } else if (request.getParameter("ddlEstadoPosibleCliente").equals("3")) {
                    stDescripcion = "Contactar en el futuro";
                } else if (request.getParameter("ddlEstadoPosibleCliente").equals("4")) {
                    stDescripcion = "Contactado";
                } else if (request.getParameter("ddlEstadoPosibleCliente").equals("5")) {
                    stDescripcion = "Posible cliente no solicitado";
                } else if (request.getParameter("ddlEstadoPosibleCliente").equals("6")) {
                    stDescripcion = "Posible cliente perdido";
                }

                obclsEstadoPosibleCliente.setStDescripcion(stDescripcion);

                //lo asigno al modelo padre
                obclsPosiblesClientes.setObclsEstadoPosibleCliente(obclsEstadoPosibleCliente);
            }//fin if hijo

            if (request.getParameter("ddlSector") != null) {
                //modelo hijo
                obclsSector.setInCodigo(Integer.parseInt(request.getParameter("ddlSector")));

                String stDescripcion = "";

                if (request.getParameter("ddlSector").equals("1")) {
                    stDescripcion = "None";

                } else if (request.getParameter("ddlSector").equals("2")) {
                    stDescripcion = "APS(Proveedro de servicio de aplicaciones)";
                } else if (request.getParameter("ddlSector").equals("3")) {
                    stDescripcion = "OEM de datos";
                } else if (request.getParameter("ddlSector").equals("4")) {
                    stDescripcion = "ERP(Planificacion de recursos de empresa)";
                } else if (request.getParameter("ddlSector").equals("5")) {
                    stDescripcion = "Gobierno/Ejercito";
                } else if (request.getParameter("ddlSector").equals("6")) {
                    stDescripcion = "Empresa grande";
                }

                obclsSector.setStDescripcion(stDescripcion);

                //lo asigno al modelo padre
                obclsPosiblesClientes.setObclsSector(obclsSector);
            }//fin if hijo

            if (request.getParameter("txtCantidadEmpleados") != null) {

                obclsPosiblesClientes.setInCatidadEmpleados(Integer.parseInt(request.getParameter("txtCantidadEmpleados")));
            }
            if (request.getParameter("txtIngresosAnuales") != null) {

                obclsPosiblesClientes.setDbIngresosAnuales(Double.parseDouble(request.getParameter("txtIngresosAnuales")));
            }

            if (request.getParameter("ddlCalificacion") != null) {
                //modelo hijo
                obclsCalificacion.setInCodigo(Integer.parseInt(request.getParameter("ddlCalificacion")));

                String stDescripcion = "";

                if (request.getParameter("ddlCalificacion").equals("1")) {
                    stDescripcion = "None";

                } else if (request.getParameter("ddlCalificacion").equals("2")) {
                    stDescripcion = "Adquirido";
                } else if (request.getParameter("ddlSector").equals("3")) {
                    stDescripcion = "Activo";
                } else if (request.getParameter("ddlCalificacion").equals("4")) {
                    stDescripcion = "Conctactado";
                } else if (request.getParameter("ddlCalificacion").equals("5")) {
                    stDescripcion = "Fallo de mercado";
                } else if (request.getParameter("ddlCalificacion").equals("6")) {
                    stDescripcion = "Proyecto cancelado";
                } else if (request.getParameter("ddlCalificacion").equals("7")) {
                    stDescripcion = "Apagar";
                }

                obclsCalificacion.setStDescripcion(stDescripcion);

                //lo asigno al modelo padre
                obclsPosiblesClientes.setObclsCalificacion(obclsCalificacion);
            }//fin if hijo

            if (request.getParameter("chkNoParticipacionCorreoElectronico") != null) {

                char chSeleccion = request.getParameter("chkNoParticipacionCorreoElectronico").equals("on")
                        ? 'S' : 'N';

                obclsPosiblesClientes.setChNlNoParticipacionCorreoElectronico(chSeleccion);
            } else {
                obclsPosiblesClientes.setChNlNoParticipacionCorreoElectronico('N');
            }

            if (request.getParameter("txtIDkype") != null) {

                obclsPosiblesClientes.setStIDSkype(request.getParameter("txtIDkype"));
            }
            if (request.getParameter("txtTwitter") != null) {

                obclsPosiblesClientes.setStTwitter(request.getParameter("txtTwitter"));
            }
            if (request.getParameter("txtCorreoElectronicoSecundario") != null) {

                obclsPosiblesClientes.setStCorreoElectronicoSecundario(request.getParameter("txtCorreoElectronicoSecundario"));
            }

            HttpSession session = request.getSession(true);

            //declaro la lista de los objectos donde se alamcena los modelos de los posibles clientes
            List<Models.clsPosiblesClientes> lstclsPosiblesClientes
                    = new ArrayList<Models.clsPosiblesClientes>();

            if (session.getAttribute("session_lstclsPosiblesClientes") != null) {

                lstclsPosiblesClientes = (List<Models.clsPosiblesClientes>) session.getAttribute("session_lstclsPosiblesClientes");

            }
            //codigo que identifica registro
            int inCodigo = lstclsPosiblesClientes.size() + 1;
            obclsPosiblesClientes.setInCodigo(inCodigo);

            //agrega el objeto a la lista 
            lstclsPosiblesClientes.add(obclsPosiblesClientes);
            //variable de session
            session.setAttribute("session_lstclsPosiblesClientes", lstclsPosiblesClientes);

            //definicion parametros desde el controlador
            request.setAttribute("stMensaje", "Se realizo Proceso con exito");
            request.setAttribute("stTipo", "success");

            //redireccion y envio de valores
            request.getRequestDispatcher("PosiblesClientes.jsp").forward(request, response);

        } catch (Exception ex) {

            //definicion parametros desde el controlador
            request.setAttribute("stMensaje", ex.getMessage());
            request.setAttribute("stTipo", "error");

            //redireccion y envio de valores
            request.getRequestDispatcher("PosiblesClientes.jsp").forward(request, response);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
