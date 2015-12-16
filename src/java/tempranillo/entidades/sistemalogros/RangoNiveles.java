/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tempranillo.entidades.sistemalogros;

/**
 *
 * @author Oscar CN
 */
  public enum RangoNiveles {

        Aprendiz(0),
        Catador(100),
        GranCatador(250),
        Narizdeoro(500),
        Bacus(1200);
        private final int puntuacionRequerida;

        protected int puntuacionRequerida() {
            return puntuacionRequerida;
        }

        /***
         * Constructor RangoNiveles. Inicializa la propiedad de la puntuación requerida con el valor adecuado.
         * @param _puntReq 
         */
        private RangoNiveles(int _puntReq) {
            this.puntuacionRequerida = _puntReq;
        }

        /***
         * Metodo estatico que obtiene el Nivel en función de la puntuación.
         * @param puntuacion
         * @return 
         */
        public static RangoNiveles getRango(int puntuacion) {
            RangoNiveles _nivel = Aprendiz;
            if (puntuacion < RangoNiveles.Catador.puntuacionRequerida) {
                _nivel = Aprendiz;
            } else if (puntuacion > RangoNiveles.Catador.puntuacionRequerida && puntuacion < RangoNiveles.GranCatador.puntuacionRequerida) {
                _nivel = Catador;
            } else if (puntuacion > RangoNiveles.GranCatador.puntuacionRequerida && puntuacion < RangoNiveles.Narizdeoro.puntuacionRequerida) {
                _nivel = GranCatador;
            } else if (puntuacion > RangoNiveles.Narizdeoro.puntuacionRequerida && puntuacion < RangoNiveles.Bacus.puntuacionRequerida) {
                _nivel = Narizdeoro;
            } else if (puntuacion > RangoNiveles.Bacus.puntuacionRequerida) {
                _nivel = Bacus;
            }
            return _nivel;
        }
    }
  
