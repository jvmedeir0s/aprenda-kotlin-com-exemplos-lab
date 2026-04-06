enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

// Evolução: Adicionado 'nome' e 'email' para identificar o usuário
data class Usuario(val nome: String, val email: String)

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, val nivel: Nivel, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        // Implementação da matrícula: adiciona o usuário à lista de inscritos
        if (!inscritos.contains(usuario)) {
            inscritos.add(usuario)
            println("Sucesso: Usuário ${usuario.nome} matriculado na formação $nome.")
        } else {
            println("Aviso: Usuário ${usuario.nome} já está matriculado nesta formação.")
        }
    }
    
    fun imprimirGrade() {
        println("--- Grade da Formação: $nome ($nivel) ---")
        conteudos.forEach { println("- ${it.nome} (${it.duracao} min)") }
        println("------------------------------------------")
    }
}

fun main() {
    // 1. Criando conteúdos educacionais
    val cursoKotlinBasico = ConteudoEducacional("Dominando a Linguagem Kotlin", 120)
    val cursoGit = ConteudoEducacional("Versionamento com Git", 60)
    val cursoEstruturaDados = ConteudoEducacional("Estruturas de Dados em Kotlin", 180)

    // 2. Criando uma formação e adicionando os conteúdos
    val formacaoAndroid = Formacao(
        nome = "Desenvolvimento Android Profissional",
        nivel = Nivel.INTERMEDIARIO,
        conteudos = listOf(cursoKotlinBasico, cursoGit, cursoEstruturaDados)
    )

    // 3. Criando usuários
    val aluno1 = Usuario("Alice Silva", "alice@email.com")
    val aluno2 = Usuario("Bruno Souza", "bruno@email.com")

    // 4. Simulando cenários de teste
    formacaoAndroid.imprimirGrade()
    
    formacaoAndroid.matricular(aluno1)
    formacaoAndroid.matricular(aluno2)
    
    // Teste de matrícula duplicada
    formacaoAndroid.matricular(aluno1)

    println("\nLista de inscritos final: ${formacaoAndroid.inscritos.map { it.nome }}")
}
