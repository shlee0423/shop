const category = document.querySelectorAll('#main-menu-nav > ul > li');
const subcategory = document.querySelectorAll('#sub-menu-section > ul > li');
const subsection = document.getElementById('sub-menu-section');


for (let i = 0; i<category.length; i++){
    category[i].addEventListener('mouseover', () => {
        subcategory[i].toggleAttribute('active', true);
    });
    category[i].addEventListener('mouseout', () => {
        subcategory[i].toggleAttribute('active', false);
    });
}



//     li 다 가져오고
// 저기 li 다 가져와서
// 매칭
// 애트리뷰트
// 토글
// onmouseenter()
// onmouseleave()
