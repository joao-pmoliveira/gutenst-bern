import '@/app/globals.css';
import BookCard from "@/components/BookCard";
import SearchBar from '@/components/SearchBar';
import { mapBookApi } from '@/lib/BookMapping';
import { BookApi } from "@/types/Api";
import { Book } from "@/types/Book";

export default async function Home() {

    const books: Book[] = await getBooks();

    return (
        <main>
            <div className=" w-fit m-auto grid grid-cols-3 gap-8 ">
                {books.map(book => <BookCard book={book} key={book.id} />)}
            </div>
        </main>
    );
}

async function getBooks(): Promise<Book[]> {
    const res = await fetch('http://localhost:8080/api/catalog', {
        next: { revalidate: 0 },
    });

    if (!res.ok) throw new Error("Failed to fetch books");

    const data: BookApi[] = await res.json();

    return data.map(book => mapBookApi(book));
}
