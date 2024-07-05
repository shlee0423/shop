const emblaNode = document.querySelector('.embla');
const options = { loop: true }
const autoPlayPlugin = EmblaCarouselAutoplay();
autoPlayPlugin.delay = 100;
autoPlayPlugin.play = true;
const plugins = [autoPlayPlugin];
const emblaApi = EmblaCarousel(emblaNode, options, plugins);
console.log(emblaApi);




