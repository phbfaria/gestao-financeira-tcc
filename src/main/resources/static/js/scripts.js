// Exemplo simples de JavaScript para interação básica

// Exibe uma mensagem de confirmação antes de deletar
function confirmarExclusao(event, mensagem) {
    if (!confirm(mensagem)) {
        event.preventDefault();
    }
}

// Exemplo de inicialização, se precisar futuramente
window.onload = function() {
    console.log("Scripts carregados com sucesso!");
};
