/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.modelo;

import java.util.ArrayList;
import java.util.List;
import tempranillo.entidades.sistemalogros.Logro;
import tempranillo.entidades.sistemalogros.TipoAccion;

/**
 *
 * @author Jose Adame Esta clase controla todos los eventos para comprobar los
 * logros y puntuaciones de los usuarios.
 *
 */
public class GestionEventos extends tempranillo.utils.SessionManagement {


    private GestorLogros gestorLogros = new GestorLogros();
    private GestorPuntuacion gestorPuntuacion = new GestorPuntuacion();
    private List listaLogros = null;

    private List getListaLogros(int idusuario) {
        if (listaLogros == null) {
            listaLogros = gestorLogros.obtenerListaLogrosUsuario(idusuario);
        }
        return listaLogros;
    }

    /**
     *
     * @param tipoLogro the value of tipoLogro
     * @param idUsuario the value of idUsuario
     * @param puntuacionObtenida the value of puntuacionObtenida
     */
    public List<Logro> comprobarEvento(TipoAccion tipoLogro, int idUsuario) {
        return comprobarEvento(tipoLogro, idUsuario, -1);
    }

    /**
     *
     * @param tipoLogro the value of tipoLogro
     * @param idUsuario the value of idUsuario
     * @param idVino the value of idVino
     * @param puntuacionObtenida the value of puntuacionObtenida
     */
    public List<Logro> comprobarEvento(TipoAccion tipoLogro, int idUsuario, int idVino) {
        //TODO: Debemos optimizar esta función, deberiamos obtener
        //la lista de logros de un usuario y comprobarlos con los disponibles de
        //de este modo conseguiremos realizar un única consulta en lugar de una por logro.
        List<Logro> _listaAcciones = new ArrayList<Logro>();
        Logro logroAccion = new Logro();
        Logro Logro = new Logro();
        switch (tipoLogro) {
            case ALTAVINO:
                if ((!getListaLogros(idUsuario).contains(3))&&(gestorLogros.comprobarMiPrimerAlta(idUsuario))) 
                {            
                            _listaAcciones.add(Logro.getLogroFromBD(3));                                              
                           
                }   
                else if ((!getListaLogros(idUsuario).contains(10))&&(gestorLogros.comprobarAltaMultiple(idUsuario,100))){
                    _listaAcciones.add(Logro.getLogroFromBD(10));                                                  
                }else if ((!getListaLogros(idUsuario).contains(11))&&(gestorLogros.comprobarAltaMultiple(idUsuario,200))){
                    _listaAcciones.add(Logro.getLogroFromBD(11));
                }else if ((!getListaLogros(idUsuario).contains(12))&&(gestorLogros.comprobarAltaMultiple(idUsuario,300))){
                     _listaAcciones.add(Logro.getLogroFromBD(12));
                }
                                    
                logroAccion.setNombre("Puntuacion obtenida");
                logroAccion.setDescripcion("Alta de vino");
                logroAccion.setPuntuacion(TipoAccion.ALTAVINO.getPuntuacion());                
                _listaAcciones.add(logroAccion);
                break;
                
            case CHECKIN:
                //Aqui comprobaremos todos los logros referentes a checkins
                //TODO: Cambiar IdLogro de Número a Cadena (más descriptivo y mantenible a futuro).
                
                if ((!getListaLogros(idUsuario).contains(2))&&(gestorLogros.comprobarMiPrimerCheckin(idUsuario))){
                    _listaAcciones.add(Logro.getLogroFromBD(2));
                }else if ((!getListaLogros(idUsuario).contains(5))&&(gestorLogros.comprobarCheckinMultiple(idUsuario,100))){
                    _listaAcciones.add(Logro.getLogroFromBD(5));                                                  
                }else if ((!getListaLogros(idUsuario).contains(6))&&(gestorLogros.comprobarCheckinMultiple(idUsuario,200))){
                    _listaAcciones.add(Logro.getLogroFromBD(6));
                }else if ((!getListaLogros(idUsuario).contains(7))&&(gestorLogros.comprobarCheckinMultiple(idUsuario,300))){
                     _listaAcciones.add(Logro.getLogroFromBD(7));
                }else if ((!getListaLogros(idUsuario).contains(20))&&(gestorLogros.comprobarSommeliers(idUsuario, idVino))){
                    _listaAcciones.add(Logro.getLogroFromBD(20));
                }            
                
                logroAccion.setDescripcion("Checkin de un Vino");
                logroAccion.setPuntuacion(TipoAccion.CHECKIN.getPuntuacion());                
                _listaAcciones.add(logroAccion);
                
                break;
            case COMENTARIOVINO:
                if ((!getListaLogros(idUsuario).contains(4))&&(gestorLogros.comprobarMiPrimerComentario(idUsuario)))
                    _listaAcciones.add(Logro.getLogroFromBD(4));
                               
                logroAccion.setDescripcion("Comentario de un Vino");
                logroAccion.setPuntuacion(TipoAccion.COMENTARIOVINO.getPuntuacion());                
                _listaAcciones.add(logroAccion);
                break;
            case SEGUIMIENTO:
                if ((!getListaLogros(idUsuario).contains(9))&&(gestorLogros.comprobarMiPrimerSeguidor(idUsuario)))
                    _listaAcciones.add(Logro.getLogroFromBD(9));
                
                logroAccion.setDescripcion("Has conseguido un seguidor"); //TODO: Cambiar esta descripción.
                logroAccion.setPuntuacion(TipoAccion.SEGUIMIENTO.getPuntuacion());                
                _listaAcciones.add(logroAccion);
                
                
                break;
            case VOTOPOSITIVO:
               logroAccion.setDescripcion("Gracias por Votar"); //TODO: Cambiar esta descripción.
                logroAccion.setPuntuacion(TipoAccion.VOTOPOSITIVO.getPuntuacion());                
                _listaAcciones.add(logroAccion);
               break;
            case LISTA:
               if ((!getListaLogros(idUsuario).contains(8))&&(gestorLogros.comprobarMiPrimeraLista(idUsuario)))
                   _listaAcciones.add(Logro.getLogroFromBD(8));
               
              logroAccion.setDescripcion("Lista creada con exito");
              logroAccion.setPuntuacion(TipoAccion.LISTA.getPuntuacion());                
              _listaAcciones.add(logroAccion);
              break;
        }
        
     String nuevonivel=  gestorPuntuacion.sumarPuntuacion(_listaAcciones, idUsuario);
     if(nuevonivel!=null)
     {
         Logro logronivel = new Logro();
         logronivel.setNombre("¡Vas evolucionando!");
         logronivel.setDescripcion("¡Nuevo nivel! "+nuevonivel);
         _listaAcciones.add(logronivel);
     
     }
        return _listaAcciones;
        
    }
}
