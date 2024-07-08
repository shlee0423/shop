const mainMenuLies = document.querySelectorAll('#main-menu-nav li');
const subMenuLies = document.querySelectorAll('#sub-menu-section > ul > li');
const header = document.querySelector('header');


for (let i = 0; i<mainMenuLies.length; i++){
    mainMenuLies[i].addEventListener('mouseover', () => {
        subMenuLies.forEach(subMenuLi => {
            subMenuLi.toggleAttribute('active', false);
        })
        subMenuLies[i].toggleAttribute('active', true);
    });
}
header.addEventListener('mouseleave', () => {
    subMenuLies.forEach(subMenuLi =>{
        subMenuLi.toggleAttribute('active', false);
    })
})



//     li 다 가져오고
// 저기 li 다 가져와서
// 매칭
// 애트리뷰트
// 토글
// onmouseenter()
// onmouseleave()
