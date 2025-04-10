package User;

public class Player {
        private int numeroDeRegistro;
        private Personaje personaje;

        public player(String nombre, String nick, String password, int numeroDeRegistro) {
            super(nombre, nick, password);
            this.numeroDeRegistro = numeroDeRegistro;
        }

        public void desafiar(String nick) {
        }

        public void aceptarDesafio(Desafio d) {
        }

        public Ranking consultarRanking() {
            return new Ranking();
        }

        public void registrarPersonaje() {
        }

        public void darDeBajaPersonaje() {
            personaje = null;
        }

        public void elegirArmas() {
        }

        public void elegirArmaduras() {
        }

        public void rechazarDesafio() {
        }

        public void consultarOro() {
        }

        public void Operation1() {
        }

        // Getters y setters opcionales
        public int getNumeroDeRegistro() {
            return numeroDeRegistro;
        }

        public void setNumeroDeRegistro(int numeroDeRegistro) {
            this.numeroDeRegistro = numeroDeRegistro;
        }

        public Personaje getPersonaje() {
            return personaje;
        }

        public void setPersonaje(Personaje personaje) {
            this.personaje = personaje;
        }
    }


