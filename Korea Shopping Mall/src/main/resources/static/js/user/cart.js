const orderForm = document.getElementById('order-form');
const productContainers = document.getElementsByClassName('product')
const csrfToken = orderForm.querySelector('input[name="_csrf"]');
[...productContainers].forEach(productContainer => {
    const cartNoInput = productContainer.querySelector('input[name=no]');
    const cartRmBtn = productContainer.querySelector('button');
    cartRmBtn.addEventListener('click', (e) => {
        fetch('/user/cart', {
            method: "DELETE",
            headers: {"X-CSRF-TOKEN": csrfToken},
            body: JSON.stringify([+cartNoInput.value])
        });
    });
});