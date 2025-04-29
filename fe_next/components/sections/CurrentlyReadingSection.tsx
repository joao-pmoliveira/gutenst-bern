import { Book } from "@/types/Book";
import { BookOpenCheck } from "lucide-react";
import BookCardProgress from "../book/BookCardProgress";
import getBooks from "@/lib/fetch";

export default async function CurrentlyReadingSection({ className = "" }) {
    const books: Book[] = await getBooks();

    return (
        <div className={`${className}`} >
            <h1 className="text-2xl mb-6 font-bold flex items-center gap-2">
                <BookOpenCheck size={24} strokeWidth={2} />
                Current Reading
            </h1>
            <div className="flex flex-col gap-8
                overflow-hidden ">
                {books.slice(0, 3).map(b => (
                    <BookCardProgress key={b.id} book={b} />
                ))}
            </div>
        </div>
    )
}
