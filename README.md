Gestão de Projetos - Sistema Desktop
Status: Projeto Concluído (Versão para Entrega Acadêmica - Setembro/2025)

📖 Descrição do Projeto
Este projeto é um sistema de gestão de projetos e equipes, desenvolvido como solução para a Unidade Curricular de Programação de Soluções Computacionais. O objetivo é criar uma plataforma desktop centralizada para otimizar o gerenciamento de projetos, superando os desafios de processos manuais e descentralizados, como o uso de múltiplas planilhas.

O sistema permite o controle efetivo dos projetos, a atribuição de tarefas a colaboradores e o acompanhamento do andamento das atividades, garantindo a integridade e a segurança das informações através de um banco de dados relacional.

✨ Funcionalidades Principais
Autenticação Segura: Tela de login para garantir que apenas usuários autorizados acessem o sistema.

Dashboard de Navegação: Um menu principal que serve como ponto de partida para todas as funcionalidades.

Módulo de Projetos: Cadastro de novos projetos e listagem de todos os projetos existentes em uma tabela, com atualização em tempo real.

Módulo de Usuários: Cadastro de novos usuários (colaboradores, gerentes, etc.) com diferentes perfis de acesso.

Módulo de Tarefas: Cadastro básico de novas tarefas, com associação a um projeto e a um usuário responsável.

Interface Intuitiva: Telas com componentes dinâmicos (como listas de seleção carregadas do banco de dados) e melhorias de usabilidade (como navegação com a tecla "Enter").

🛠️ Arquitetura e Tecnologias
A arquitetura do sistema foi projetada para ser robusta e organizada, seguindo padrões de mercado.

Linguagem: Java (JDK 21 LTS)

Interface Gráfica: Biblioteca Swing

Banco de Dados: MySQL

IDE: IntelliJ IDEA

Testes: JUnit 5 para testes unitários da camada de acesso a dados.

Padrão de Arquitetura:

MVC (Model-View-Controller): Para separar a lógica de dados (Model), da interface (View) e do controle de ações (Controller).

DAO (Data Access Object): Para isolar e centralizar a lógica de persistência com o banco de dados.

Factory: Para gerenciar a criação de conexões com o banco de dados.

🗃️ Estrutura do Banco de Dados (DER)
A base do sistema é um banco de dados relacional que garante a integridade dos dados. O diagrama abaixo ilustra as entidades e seus relacionamentos.

(Instrução: Faça o upload da imagem do seu diagrama para o repositório do GitHub e substitua o link abaixo pelo link da sua imagem)

🚀 Como Executar o Projeto
Para executar este projeto, siga os passos abaixo:

1. Pré-requisitos:

Java JDK 21 ou superior.

MySQL Server.

Uma IDE Java, como o IntelliJ IDEA.

2. Configuração do Banco de Dados:

Crie um novo schema (banco de dados) no seu MySQL chamado gestao_projetos.

Execute o script SQL completo (que te passei anteriormente) para criar todas as tabelas e relacionamentos.

3. Configuração da Conexão:

Dentro do código, no arquivo src/factory/ConnectionFactory.java, verifique e, se necessário, altere as credenciais de acesso ao banco de dados (usuário e senha) para corresponderem à sua instalação do MySQL.

4. Execução:

Abra o projeto no IntelliJ IDEA.

O ponto de entrada principal da aplicação é a classe view/TelaLogin.java.

Execute o método main desta classe para iniciar o sistema.

👥 Autores
Nome	Função
Pablo Henrique Santana Bernardino	Desenvolvimento Back-end, DAO e Testes
Jessica Vieira Ramos Gonzaga	Desenvolvimento Front-end (Views) e Apresentação

Exportar para as Planilhas
