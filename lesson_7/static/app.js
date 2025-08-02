// Загрузка главной страницы
function loadHome() {
  const content = `
        <div class="jumbotron bg-light p-5 rounded-lg">
            <h1 class="display-4">Добро пожаловать в TechShop SPA!</h1>
            <p class="lead">Интерактивный магазин с динамической загрузкой</p>
            <hr class="my-4">
            <p>Нажмите кнопку ниже, чтобы увидеть товары без перезагрузки страницы.</p>
            <button class="btn btn-primary btn-lg" onclick="loadProducts()">Показать товары</button>
        </div>
    `;
  document.getElementById('app-content').innerHTML = content;
  updateActiveNav('home');
}

// Загрузка списка товаров
async function loadProducts() {
  try {
    const response = await fetch('/api/products');
    const products = await response.json();

    let productsHtml = '<h1 class="mb-4">Каталог товаров</h1><div class="row">';

    products.forEach(product => {
      productsHtml += `
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">${product.name}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${product.price} руб.</h6>
                            <p class="card-text">${product.description}</p>
                            <span class="badge bg-secondary">${product.category}</span>
                        </div>
                        <div class="card-footer bg-white">
                            <button class="btn btn-primary" onclick="loadProductDetail(${product.id})">Подробнее</button>
                        </div>
                    </div>
                </div>
            `;
    });

    productsHtml += '</div>';
    document.getElementById('app-content').innerHTML = productsHtml;
    updateActiveNav('products');
  } catch (error) {
    console.error('Ошибка загрузки товаров:', error);
    document.getElementById('app-content').innerHTML = `
            <div class="alert alert-danger">Не удалось загрузить товары. Пожалуйста, попробуйте позже.</div>
        `;
  }
}

// Загрузка детальной информации о товаре
async function loadProductDetail(productId) {
  try {
    const response = await fetch(`/api/product/${productId}`);
    const product = await response.json();

    if (product.error) {
      throw new Error(product.error);
    }

    const productHtml = `
            <div class="row">
                <div class="col-md-6">
                    <img src="https://via.placeholder.com/400x300?text=${product.name}" class="img-fluid rounded" alt="${product.name}">
                </div>
                <div class="col-md-6">
                    <h1>${product.name}</h1>
                    <h3 class="text-primary">${product.price} руб.</h3>
                    <span class="badge bg-secondary">${product.category}</span>
                    <p class="mt-3">${product.description}</p>
                    
                    <div class="mt-4">
                        <button class="btn btn-success btn-lg">Добавить в корзину</button>
                        <button class="btn btn-outline-secondary btn-lg ms-2" onclick="loadProducts()">Назад к списку</button>
                    </div>
                    
                    <div class="mt-5">
                        <h4>Характеристики</h4>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th>Категория</th>
                                    <td>${product.category}</td>
                                </tr>
                                <tr>
                                    <th>Цена</th>
                                    <td>${product.price} руб.</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        `;

    document.getElementById('app-content').innerHTML = productHtml;
    updateActiveNav('products');
  } catch (error) {
    console.error('Ошибка загрузки товара:', error);
    document.getElementById('app-content').innerHTML = `
            <div class="alert alert-danger">Не удалось загрузить информацию о товаре: ${error.message}</div>
            <button class="btn btn-secondary" onclick="loadProducts()">Вернуться к списку товаров</button>
        `;
  }
}

// Обновление активного пункта меню
function updateActiveNav(activeItem) {
  document.querySelectorAll('.nav-link').forEach(link => {
    link.classList.remove('active');
  });

  if (activeItem === 'home') {
    document.querySelector('.nav-link[onclick="loadHome()"]').classList.add('active');
  } else if (activeItem === 'products') {
    document.querySelector('.nav-link[onclick="loadProducts()"]').classList.add('active');
  }
}

// Загружаем главную страницу при старте
window.onload = loadHome;