// ===== DOM Elements =====
const navLinks = document.querySelectorAll('.nav-links a');
const menuToggle = document.getElementById('menuToggle');
const navList = document.querySelector('.nav-links');
const darkToggle = document.getElementById('darkModeToggle');
const visitorCounterEl = document.getElementById('visitorCounter');
const projectFilter = document.getElementById('projectFilter');
const clearFilter = document.getElementById('clearFilter');
const projectCards = document.querySelectorAll('.project-card');
const resumeUpload = document.getElementById('resumeUpload');
const resumeLink = document.getElementById('resumeLink');
const resumeHint = document.getElementById('resumeHint');
const yearEl = document.getElementById('year');
const testResults = document.getElementById('testResults');

// ===== Mobile Menu =====
menuToggle.addEventListener('click', () => {
  navList.classList.toggle('show');
});

// ===== Smooth Scroll =====
navLinks.forEach(link => {
  link.addEventListener('click', e => {
    e.preventDefault();
    const id = link.getAttribute('href');
    document.querySelector(id).scrollIntoView({ behavior: 'smooth' });
    navList.classList.remove('show');
  });
});

// ===== Dark Mode =====
function setTheme(light) {
  document.body.classList.toggle('light', light);
  darkToggle.textContent = light ? '☀️' : '🌙';
}
setTheme(localStorage.getItem('wp_theme') === 'light');
darkToggle.addEventListener('click', () => {
  const light = !document.body.classList.contains('light');
  setTheme(light);
  localStorage.setItem('wp_theme', light ? 'light' : 'dark');
});

// ===== Visitor Counter =====
let count = Number(localStorage.getItem('visitorCount') || 0) + 1;
localStorage.setItem('visitorCount', count);
visitorCounterEl.textContent = count;

// ===== Project Filter =====
function applyFilter(term) {
  term = term.toLowerCase();
  projectCards.forEach(card => {
    const text = card.textContent.toLowerCase();
    card.style.display = text.includes(term) ? '' : 'none';
  });
}
projectFilter.addEventListener('input', e => applyFilter(e.target.value));
clearFilter.addEventListener('click', () => {
  projectFilter.value = '';
  applyFilter('');
});

// ===== Resume Upload =====
resumeUpload.addEventListener('change', () => {
  const file = resumeUpload.files[0];
  if (!file) return;
  const url = URL.createObjectURL(file);
  resumeLink.href = url;
  resumeLink.download = file.name;
  resumeLink.textContent = `Download ${file.name}`;
  resumeHint.textContent = 'Ready to download!';
});

// ===== Contact Form =====
document.getElementById('contactForm').addEventListener('submit', e => {
  e.preventDefault();
  alert('Thank you! Your message has been sent.');
  e.target.reset();
});

// ===== Footer Year =====
yearEl.textContent = new Date().getFullYear();
