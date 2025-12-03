import tkinter as tk
from tkinter import ttk, messagebox, simpledialog
import pickle
import datetime

# ==================== MODELO ====================

class Autor:
    def __init__(self, nombre, nacionalidad):
        self.nombre = nombre
        self.nacionalidad = nacionalidad
    def __str__(self):
        return f"{self.nombre} ({self.nacionalidad})"

class Estudiante:
    def __init__(self, codigo, nombre):
        self.codigo = codigo
        self.nombre = nombre
    def __str__(self):
        return f"{self.nombre} [{self.codigo}]"

class Libro:
    def __init__(self, titulo, isbn, paginas):
        self.titulo = titulo
        self.isbn = isbn
        self.paginas = paginas
    def __str__(self):
        return f"{self.titulo} (ISBN {self.isbn})"

class Prestamo:
    def __init__(self, estudiante, libro):
        self.estudiante = estudiante
        self.libro = libro
        self.fecha = datetime.date.today()
    def __str__(self):
        return f"{self.estudiante} → {self.libro} ({self.fecha})"

class Biblioteca:
    def __init__(self, nombre):
        self.nombre = nombre
        self.autores = []
        self.libros = []
        self.prestamos = []

# ==================== PERSISTENCIA ====================

def guardar(biblio):
    with open("biblioteca.dat", "wb") as f:
        pickle.dump(biblio, f)
    messagebox.showinfo("Guardado", "Datos guardados correctamente")

def cargar():
    try:
        with open("biblioteca.dat", "rb") as f:
            return pickle.load(f)
    except:
        return None

# ==================== INTERFAZ ====================

class App:
    def __init__(self, root):
        self.root = root
        self.root.title("📚 Sistema de Biblioteca UMSA")
        self.root.geometry("850x600")
        self.root.configure(bg="#e8eaf6")  # Fondo suave

        self.biblioteca = Biblioteca("UMSA")

        # ---- Estilo general ----
        style = ttk.Style()
        style.configure("TButton", font=("Arial", 11), padding=6)
        style.configure("TLabel", background="#e8eaf6", foreground="#1a237e", font=("Arial", 12, "bold"))

        # -------- MENÚ SUPERIOR --------
        barra = tk.Menu(self.root)
        self.root.config(menu=barra)

        menu_archivo = tk.Menu(barra, tearoff=0)
        menu_archivo.add_command(label="Guardar", command=lambda: guardar(self.biblioteca))
        menu_archivo.add_command(label="Cargar", command=self.cargar_datos)
        menu_archivo.add_separator()
        menu_archivo.add_command(label="Salir", command=self.root.quit)

        barra.add_cascade(label="Archivo", menu=menu_archivo)

        menu_registro = tk.Menu(barra, tearoff=0)
        menu_registro.add_command(label="Agregar Autor", command=self.agregar_autor)
        menu_registro.add_command(label="Agregar Libro", command=self.agregar_libro)
        barra.add_cascade(label="Registro", menu=menu_registro)

        menu_prestamo = tk.Menu(barra, tearoff=0)
        menu_prestamo.add_command(label="Crear Préstamo", command=self.crear_prestamo)
        barra.add_cascade(label="Préstamos", menu=menu_prestamo)

        menu_ayuda = tk.Menu(barra, tearoff=0)
        menu_ayuda.add_command(label="Acerca de", command=lambda: messagebox.showinfo("Acerca de", "Sistema de Biblioteca UMSA\nVersión Mejorada"))
        barra.add_cascade(label="Ayuda", menu=menu_ayuda)

        # -------- PANEL LATERAL --------
        panel = tk.Frame(self.root, bg="#c5cae9", width=200)
        panel.pack(side="left", fill="y")

        tk.Label(panel, text="Menú Rápido", bg="#c5cae9", fg="#1a237e",
                 font=("Arial", 14, "bold")).pack(pady=15)

        botones = [
            ("Agregar Autor", self.agregar_autor),
            ("Agregar Libro", self.agregar_libro),
            ("Crear Préstamo", self.crear_prestamo),
            ("Guardar Datos", lambda: guardar(self.biblioteca)),
            ("Cargar Datos", self.cargar_datos),
        ]

        for txt, cmd in botones:
            ttk.Button(panel, text=txt, command=cmd).pack(pady=6, fill="x", padx=15)

        # -------- ÁREA DE ESTADO --------
        self.text_area = tk.Text(self.root, width=70, height=30, font=("Consolas", 11), bg="#ffffff")
        self.text_area.pack(pady=15, padx=10)

        self.actualizar_estado()

    # ==================== LÓGICA ====================

    def agregar_autor(self):
        nombre = simpledialog.askstring("Autor", "Nombre del autor:")
        nacionalidad = simpledialog.askstring("Autor", "Nacionalidad:")
        if nombre and nacionalidad:
            self.biblioteca.autores.append(Autor(nombre, nacionalidad))
            self.actualizar_estado()

    def agregar_libro(self):
        titulo = simpledialog.askstring("Libro", "Título del libro:")
        isbn = simpledialog.askstring("Libro", "ISBN:")
        if titulo and isbn:
            paginas = ["Página 1... contenido de ejemplo"]
            self.biblioteca.libros.append(Libro(titulo, isbn, paginas))
            self.actualizar_estado()

    def crear_prestamo(self):
        if not self.biblioteca.libros:
            messagebox.showwarning("Error", "No hay libros disponibles.")
            return

        codigo = simpledialog.askstring("Estudiante", "Código del estudiante:")
        nombre = simpledialog.askstring("Estudiante", "Nombre del estudiante:")
        estudiante = Estudiante(codigo, nombre)

        ventana = tk.Toplevel(self.root)
        ventana.title("Seleccionar Libro")
        ventana.geometry("300x150")

        tk.Label(ventana, text="Seleccione Libro:", font=("Arial", 12)).pack(pady=10)

        combo = ttk.Combobox(ventana, values=[str(l) for l in self.biblioteca.libros])
        combo.pack()

        def confirmar():
            if combo.current() == -1:
                messagebox.showerror("Error", "Debe seleccionar un libro.")
                return
            libro = self.biblioteca.libros[combo.current()]
            self.biblioteca.prestamos.append(Prestamo(estudiante, libro))
            ventana.destroy()
            self.actualizar_estado()

        ttk.Button(ventana, text="Aceptar", command=confirmar).pack(pady=10)

    def cargar_datos(self):
        data = cargar()
        if data:
            self.biblioteca = data
            self.actualizar_estado()
            messagebox.showinfo("Cargado", "Datos cargados correctamente")

    def actualizar_estado(self):
        self.text_area.delete("1.0", tk.END)
        t = f"""
📚 Biblioteca: {self.biblioteca.nombre}

Autores:
{chr(10).join([' - ' + str(a) for a in self.biblioteca.autores]) or '   (vacío)'}

Libros:
{chr(10).join([' - ' + str(l) for l in self.biblioteca.libros]) or '   (vacío)'}

Préstamos:
{chr(10).join([' - ' + str(p) for p in self.biblioteca.prestamos]) or '   (vacío)'}
"""
        self.text_area.insert(tk.END, t)


# ==================== MAIN ====================

root = tk.Tk()
app = App(root)
root.mainloop()
