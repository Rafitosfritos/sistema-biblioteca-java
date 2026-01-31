import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Livro {
    String titulo;
    String autor;
    int paginas;
    boolean emprestado;
    LocalDate dataDeDevolucao;

    public void exibirInformações() {
        System.out.printf("""
                \nNome do livro: %s
                Nome do autor: %s
                Número de páginas: %d \n""", titulo, autor, paginas);

        if (!this.emprestado) {
            System.out.println("Status: Disponível");
        } else {
            System.out.println("Status: Emprestado");
            System.out.println("Previsão de Devolução: " + this.formatarData(this.dataDeDevolucao));
        }
    }

    public void empretaLivro() {
        this.emprestado = true;
        this.dataDeDevolucao = LocalDate.now().plusDays(15);
    }

    public String formatarData(LocalDate data) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formato);
    }
}
