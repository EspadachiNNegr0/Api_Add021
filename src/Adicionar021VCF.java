import java.io.*;

public class Adicionar021VCF {
    public static void main(String[] args) {
        String entrada = "D:\\SSD Doc\\Documents\\Programing\\Java\\Adicionar021\\Adicionar021VCF\\src\\contacts\\Contacts.vcf";
        String saida = "D:\\SSD Doc\\Documents\\Programing\\Java\\Adicionar021\\Adicionar021VCF\\src\\contacts\\Contacts_021.vcf";

        int contador = 0;

        try (
                BufferedReader br = new BufferedReader(new FileReader(entrada));
                BufferedWriter bw = new BufferedWriter(new FileWriter(saida))
        ) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("TEL") && linha.contains(":")) {
                    String[] partes = linha.split(":", 2);
                    String cabecalho = partes[0];
                    String numero = partes[1];

                    if (numero.startsWith("+55")) {
                        numero = numero.substring(3);
                    }

                    // Ignora se já começar com 021
                    if (!numero.startsWith("021")) {
                        String novoNumero = "021" + numero;
                        bw.write(cabecalho + ":" + novoNumero);
                        bw.newLine();
                        System.out.println("Alterado: " + numero + " → " + novoNumero);
                        contador++;
                        continue;
                    }
                }

                // Mantém a linha original se não for número a ser alterado
                bw.write(linha);
                bw.newLine();
            }

            System.out.println("\n✅ Total de números alterados: " + contador);
            System.out.println("✅ Arquivo salvo em:\n" + saida);

        } catch (IOException e) {
            System.err.println("❌ Erro ao processar o arquivo:");
            e.printStackTrace();
        }
    }
}
