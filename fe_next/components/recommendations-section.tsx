import { Star } from "lucide-react";
import BookCardCompact from "./book/BookCardCompact";
import getBooks from "@/lib/fetch";

export default async function Recommendations({ className = "", ...props }) {
    const books = await getBooks();

    return (
        <section className={`${className} overflow-x-hidden`}>
            <h1 className="text-2xl mb-4 font-sans font-bold flex items-center gap-2">
                <Star size={28} strokeWidth={2} />
                Recommended
            </h1>
            <div className="flex flex-nowrap pb-4 *:min-w-30 space-x-6 overflow-x-auto">
                {books.map(book =>
                    <BookCardCompact key={book.id} book={book} />)
                }
            </div>
        </section>
    )
}
