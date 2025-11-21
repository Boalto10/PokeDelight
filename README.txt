PokeDelight - Projeto completo Java Swing com MySQL

Novidades implementadas:
- Cardápio com imagens (painéis em cartões)
- Carrinho (adicionar itens, visualizar, limpar)
- Finalizar compra / Pagamento (insere pedidos no MySQL)
- Dashboard Admin (adicionar/editar/remover itens do menu)
- Script SQL completo para criar tabelas, imagens referenciadas e dados de exemplo

Como usar:
1. Importe o projeto no NetBeans (Open Project) ou use Maven.
2. Ajuste src/main/resources/db.properties com suas credenciais MySQL.
3. Rode o script SQL: mysql -u root -p < sql/create_database_full.sql
4. Run MainWindow (classe principal).

Observações:
- As imagens estão em src/main/resources/images/itemX.png. Ao adicionar novo item no admin, coloque o caminho relativo (por exemplo src/main/resources/images/item13.png).
- Testado localmente em ambiente similar; se receber erros, me envie a stacktrace que eu corrijo.
