import { Book } from "@/types/Book";
import Link from "next/link";

export default function BookCard(props: { book: Book }) {
    const book = props.book;
    const defaultCover = "/next.svg"

    return (
        <Link href={`/book/${book.id}`} className="
            w-full max-w-48
            group relative
            cursor-pointer
        ">
            <img
                alt={book.title}
                src={book.cover || defaultCover}
                className="
                    aspect-square
                    w-full rounded-md
                    bg-gray-200
                    object-contain
                    group-hover:opacity-75
                "
            />

            <h3 className="
                font-serif text-xl font-medium
                line-clamp-2 text-balance
            ">
                {book.originalTitle}
            </h3>

            <p className="font-sans text-sm text-gray-600">
                {book.authors.map(author => author.name).join(",")}
            </p>


        </Link>
    );
}
